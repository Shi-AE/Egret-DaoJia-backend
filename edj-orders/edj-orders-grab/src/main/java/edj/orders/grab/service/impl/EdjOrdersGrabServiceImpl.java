package edj.orders.grab.service.impl;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONArray;
import co.elastic.clients.elasticsearch._types.DistanceUnit;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.GeoDistanceType;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.edj.api.api.customer.ProviderApi;
import com.edj.api.api.customer.dto.ProviderSettingsDetailDTO;
import com.edj.api.api.foundations.ConfigRegionApi;
import com.edj.api.api.foundations.dto.ConfigRegionInnerDTO;
import com.edj.cache.helper.LockHelper;
import com.edj.common.constants.ErrorInfo;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.expcetions.CommonException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.*;
import com.edj.es.core.ElasticSearchTemplate;
import com.edj.es.utils.SearchResponseUtils;
import com.edj.orders.base.domain.entity.EdjOrders;
import com.edj.orders.base.domain.entity.EdjOrdersGrab;
import com.edj.orders.base.domain.entity.EdjOrdersServe;
import com.edj.orders.base.enums.EdjOrderStatus;
import com.edj.orders.base.mapper.EdjOrdersDispatchMapper;
import com.edj.orders.base.mapper.EdjOrdersGrabMapper;
import com.edj.orders.base.mapper.EdjOrdersMapper;
import com.edj.orders.base.mapper.EdjOrdersServeMapper;
import com.edj.security.enums.EdjSysRole;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import edj.orders.grab.domain.dto.OrdersGrabInfo;
import edj.orders.grab.domain.dto.OrdersGrabListDTO;
import edj.orders.grab.domain.vo.OrdersGrabVO;
import edj.orders.grab.enums.EdjOrderOriginType;
import edj.orders.grab.enums.EdjServeStatus;
import edj.orders.grab.service.EdjOrdersGrabService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static com.edj.cache.constants.CacheConstants.CacheName.ORDERS_STOCK_CACHE;
import static com.edj.cache.constants.CacheConstants.CacheName.ORDERS_SUCCESS_SYNC_CACHE;
import static com.edj.cache.helper.LockHelper.COMPLEX_OPERATION_WAIT_TIME;
import static com.edj.common.constants.CommonRedisConstants.Lock.GRAB_ORDERS_LOCK;
import static com.edj.common.constants.IndexConstants.ORDERS_GRAB;
import static com.edj.common.constants.RedisConstants.LIMIT;
import static com.edj.common.constants.RedisConstants.QUEUE_NUM;
import static com.edj.common.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;
import static edj.orders.grab.constants.ErrorInfo.Msg.SEIZE_ORDERS_FAILED;
import static edj.orders.grab.constants.ErrorInfo.Msg.SEIZE_ORDERS_RECEIVE_CLOSED;

/**
 * 针对表【edj_orders_grab(抢单池)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/04/23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EdjOrdersGrabServiceImpl extends MPJBaseServiceImpl<EdjOrdersGrabMapper, EdjOrdersGrab> implements EdjOrdersGrabService {

    private final ProviderApi providerApi;

    private final ConfigRegionApi configRegionApi;

    private final ElasticSearchTemplate elasticSearchTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "grabOrdersScript")
    private DefaultRedisScript<Long> grabOrdersScript;

    private final LockHelper lockHelper;

    private final EdjOrdersServeMapper ordersServeMapper;

    private final EdjOrdersMapper ordersMapper;

    private final EdjOrdersDispatchMapper ordersDispatchMapper;

    @Override
    public List<OrdersGrabVO> searchList(OrdersGrabListDTO ordersGrabListDTO) {
        // 查询用户设置详情
        ProviderSettingsDetailDTO detail = providerApi.detail();

        // 校验用户是否可以查询
        if (ObjectUtils.isNull(detail)) {
            return List.of();
        }
        Integer canPickUp = detail.getCanPickUp();
        Integer settingsStatus = detail.getSettingsStatus();
        if (canPickUp != 1 || settingsStatus != 1) {
            return List.of();
        }

        // 技能
        List<Long> serveItemIdList = detail.getServeItemIdList();
        if (CollUtils.isEmpty(serveItemIdList)) {
            return List.of();
        }

        // 距离
        String cityCode = detail.getCityCode();
        Double serveDistance = ordersGrabListDTO.getServeDistance();
        if (ObjectUtils.isNull(serveDistance)) {
            // 查询区域默认配置
            ConfigRegionInnerDTO configRegionInnerDTO = configRegionApi.findByCityCode(cityCode);
            // 查询用户角色
            Set<Long> roles = SecurityUtils.getRoles();
            if (roles.contains(EnumUtils.value(EdjSysRole.WORKER, Long.class))) {
                // 个体
                serveDistance = configRegionInnerDTO.getStaffServeRadius().doubleValue();
            } else {
                // 企业
                serveDistance = configRegionInnerDTO.getInstitutionServeRadius().doubleValue();
            }
        }

        // 根据条件查询es
        Double finalServeDistance = serveDistance;
        double lon = detail.getLon().doubleValue();
        double lat = detail.getLat().doubleValue();
        Double lastRealDistance = ordersGrabListDTO.getLastRealDistance();
        Long lastId = ordersGrabListDTO.getLastId();
        SearchRequest searchRequest = new SearchRequest.Builder()
                .query(query -> query.bool(bool -> {
                    // 所在城市
                    bool.must(must -> must.term(term -> term.field(LambdaUtils.UFN(OrdersGrabInfo::getCityCode)).value(cityCode)));

                    // 服务类型
                    Long serveTypeId = ordersGrabListDTO.getServeTypeId();
                    if (ObjectUtils.isNotNull(serveTypeId)) {
                        bool.must(must -> must.term(term -> term.field(LambdaUtils.UFN(OrdersGrabInfo::getEdjServeTypeId)).value(serveTypeId)));
                    }

                    // 服务项
                    bool.must(must -> must.terms(terms -> terms
                            .field(LambdaUtils.UFN(OrdersGrabInfo::getEdjServeItemId))
                            .terms(t -> t.value(serveItemIdList.stream().map(FieldValue::of).toList()))
                    ));

                    // 距离
                    bool.must(must -> must.geoDistance(geoDistance -> geoDistance
                            .field(LambdaUtils.UFN(OrdersGrabInfo::getLocation))
                            .location(location -> location.latlon(ll -> ll.lon(lon).lat(lat)))
                            .distance(finalServeDistance + "km")
                    ));

                    // 关键字匹配
                    String keyWord = ordersGrabListDTO.getKeyWord();
                    if (StringUtils.isNotBlank(keyWord)) {
                        bool.must(must -> must.match(match -> match.field(LambdaUtils.UFN(OrdersGrabInfo::getKeyWords)).query(keyWord)));
                    }

                    // 时间范围
                    LocalDateTime minServeStartTime = ordersGrabListDTO.getMinServeStartTime();
                    LocalDateTime maxServeStartTime = ordersGrabListDTO.getMaxServeStartTime();
                    bool.must(mast -> mast.range(range -> range
                            .field(LambdaUtils.UFN(OrdersGrabInfo::getServeStartTime))
                            .from(DateUtils.format(minServeStartTime, DEFAULT_DATE_TIME_FORMAT))
                            .to(DateUtils.format(maxServeStartTime, DEFAULT_DATE_TIME_FORMAT))
                    ));

                    return bool;
                }))
                .sort(sort -> sort.geoDistance(geoDistance -> geoDistance
                        .field(LambdaUtils.UFN(OrdersGrabInfo::getLocation))
                        .distanceType(GeoDistanceType.Arc)
                        .order(SortOrder.Asc)
                        .unit(DistanceUnit.Kilometers)
                        .location(location -> location.latlon(ll -> ll.lon(lon).lat(lat)))
                ))
                .sort(sort -> sort.field(f -> f
                        .field(LambdaUtils.UFN(OrdersGrabInfo::getId))
                        .order(SortOrder.Desc)
                ))
                // 索引
                .index(ORDERS_GRAB)
                // 分页
                .searchAfter(NumberUtils.null2Default(lastRealDistance, 0))
                .searchAfter(NumberUtils.null2Default(lastId, Long.MAX_VALUE))
                .build();

        // 检索数据
        SearchResponse<OrdersGrabInfo> searchResponse = elasticSearchTemplate.opsForDoc().search(searchRequest, OrdersGrabInfo.class);
        if (SearchResponseUtils.isNotSuccess(searchResponse)) {
            return List.of();
        }

        return searchResponse.hits().hits()
                .stream()
                .map(hit -> {
                    // 获取数据
                    OrdersGrabVO ordersGrabVO = BeanUtils.toBean(hit.source(), OrdersGrabVO.class);
                    // 从 sort 字段中获取实际距离
                    double realDistance = hit.sort().getFirst().doubleValue();
                    // 设置距离
                    ordersGrabVO.setRealDistance(realDistance);
                    return ordersGrabVO;
                })
                .toList();
    }

    @Override
    public void grab(Long id) {
        // 校验用户
        ProviderSettingsDetailDTO detail = providerApi.detail();
        Integer canPickUp = detail.getCanPickUp();
        Integer settingsStatus = detail.getSettingsStatus();
        if (canPickUp != 1 || settingsStatus != 1) {
            throw new CommonException(ErrorInfo.Code.SEIZE_ORDERS_FAILED, SEIZE_ORDERS_RECEIVE_CLOSED);
        }

        // 获取用户角色
        Set<Long> roles = SecurityUtils.getRoles();
        EdjSysRole sysRole = roles.contains(EnumUtils.value(EdjSysRole.WORKER, Long.class)) ? EdjSysRole.WORKER : EdjSysRole.INSTITUTION;

        // 校验订单
        LambdaQueryWrapper<EdjOrdersGrab> wrapper = new LambdaQueryWrapper<EdjOrdersGrab>()
                .select(EdjOrdersGrab::getId)
                .eq(EdjOrdersGrab::getId, id);
        boolean exists = baseMapper.exists(wrapper);
        if (!exists) {
            throw new CommonException(ErrorInfo.Code.SEIZE_ORDERS_FAILED, SEIZE_ORDERS_FAILED);
        }

        // todo 校验时间，通过数据库查询

        // todo 校验抢单上限，通过查询数据库
        String cityCode = detail.getCityCode();
        ConfigRegionInnerDTO configRegionInnerDTO = configRegionApi.findByCityCode(cityCode);
        Integer receiveOrderMax = sysRole.equals(EdjSysRole.WORKER) ? configRegionInnerDTO.getStaffReceiveOrderMax() : configRegionInnerDTO.getInstitutionReceiveOrderMax();

        // 执行抢单
        // 准备key
        long index = id % QUEUE_NUM;
        // 库存 key
        String stockKey = String.format(ORDERS_STOCK_CACHE, index);
        // 结果同步队列
        String syncKey = String.format(ORDERS_SUCCESS_SYNC_CACHE, index);
        // 用户id
        Long userId = SecurityUtils.getUserId();

        // -- key: 库存(KEYS[1]), 抢券同步队列(KEYS[2])
        // -- argv: 订单id(ARGV[1]), 用户id(ARGV[2]), 用户角色(ARGV[3]), 是否机器抢单（1：机器抢单，0：人工抢单）(ARGV[4])

        // 执行脚本
        Long result = redisTemplate.execute(
                grabOrdersScript,
                List.of(stockKey, syncKey),
                id, userId, EnumUtils.value(sysRole), 0
        );

        if (ObjectUtils.isNull(result)) {
            throw new BadRequestException("抢单失败");
        }

        //noinspection IfCanBeSwitch
        if (result == -1 || result == -2) {
            throw new BadRequestException("已抢光");
        }
        if (result == -3) {
            throw new BadRequestException("抢券失败");
        }
    }

    @Override
    public void GrabOrdersSync() {
        // 开启多线程处理
        List<CompletableFuture<Void>> futureList = IntStream.range(0, QUEUE_NUM)
                .mapToObj(i -> AsyncUtils.runAsync(() -> completableFutureHandel(i)))
                .toList();
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
    }

    private void completableFutureHandel(int i) {
        // 获取key
        String key = String.format(ORDERS_SUCCESS_SYNC_CACHE, i);
        log.info("线程: {} -> 获取key | key: {}", i, key);

        // 加锁执行
        lockHelper.syncLock(
                String.format(GRAB_ORDERS_LOCK, key),
                COMPLEX_OPERATION_WAIT_TIME,
                () -> lockHandel(i, key)
        );
    }

    private void lockHandel(int i, String key) {
        // 获取抢单成功数据表
        redisTemplate.opsForHash()
                .scan(key, ScanOptions.scanOptions().count(LIMIT).build())
                // 遍历查询结果
                .forEachRemaining(entry -> {
                    // 保证独立流程正常执行捕获保留异常
                    try {
                        // 执行数据库处理
                        SpringUtil.getBean(this.getClass()).handle(entry);
                        // 删除 redis 库存数据
                        redisTemplate.opsForHash().delete(String.format(ORDERS_STOCK_CACHE, i), Long.valueOf((String) entry.getKey()));
                        // 删除 redis 同步队列数据
                        redisTemplate.opsForHash().delete(key, Long.valueOf((String) entry.getKey()));
                    } catch (Exception e) {
                        log.error("线程: {} -> 获取key | key: {} | 订单: {}", i, key, entry, e);
                    }
                });
    }

    @Transactional
    public void handle(Map.Entry<Object, Object> entry) {
        // 解析数据
        JSONArray result = JsonUtils.parseArray(entry.getValue());
        // 服务者id
        Long serveProviderId = result.getLong(0);
        // 用户角色
        EdjSysRole sysRole = EnumUtils.getEnum(result.getLong(1), EdjSysRole.class);
        // 是否为机器抢单
        Boolean isMachine = result.getBool(2);

        // 订单id
        Long orderId = NumberUtils.parseLong(entry.getKey().toString());

        // 获取抢单信息
        EdjOrdersGrab ordersGrab = baseMapper.selectById(orderId);
        // 校验抢单池是否存在此订单
        if (ObjectUtils.isNull(ordersGrab)) {
            // 删除
            return;
        }

        // 校验服务单是否已经生成
        LambdaQueryWrapper<EdjOrdersServe> ordersServeQueryWrapper = new LambdaQueryWrapper<EdjOrdersServe>()
                .select(EdjOrdersServe::getId)
                .eq(EdjOrdersServe::getId, orderId);
        boolean exists = ordersServeMapper.exists(ordersServeQueryWrapper);
        if (exists) {
            // 删除
            return;
        }

        // 查询订单所属人id
        LambdaQueryWrapper<EdjOrders> ordersQueryWrapper = new LambdaQueryWrapper<EdjOrders>()
                .select(EdjOrders::getEdjUserId)
                .eq(EdjOrders::getId, orderId);
        EdjOrders orders = ordersMapper.selectOne(ordersQueryWrapper);
        if (ObjectUtils.isNull(orders)) {
            // 删除
            return;
        }

        // 生成服务单
        EdjOrdersServe ordersServe = EdjOrdersServe
                .builder()
                .edjServeProvidersId(serveProviderId)
                .edjUserId(orders.getEdjUserId())
                .edjOrdersId(orderId)
                .ordersOriginType(EnumUtils.value(
                        isMachine ? EdjOrderOriginType.ASSIGN : EdjOrderOriginType.GRAB,
                        Integer.class
                ))
                .cityCode(ordersGrab.getCityCode())
                .edjServeTypeId(ordersGrab.getEdjServeTypeId())
                .serveStartTime(ordersGrab.getServeStartTime())
                .edjServeItemId(ordersGrab.getEdjServeItemId())
                .serveStatus(EnumUtils.value(
                        sysRole.equals(EdjSysRole.WORKER) ? EdjServeStatus.TO_SERVE : EdjServeStatus.TO_ASSIGN,
                        Integer.class
                ))
                .serveItemImg(ordersGrab.getServeItemImg())
                .ordersAmount(ordersGrab.getOrdersAmount())
                .purNum(ordersGrab.getPurNum())
                .sortBy(ordersGrab.getSortBy().longValue())
                .build();
        int insert = ordersServeMapper.insert(ordersServe);
        if (insert != 1) {
            // 不删除，中断执行，回滚
            throw new ServerErrorException();
        }

        // 修改订单状态
        LambdaUpdateWrapper<EdjOrders> ordersUpdateWrapper = new LambdaUpdateWrapper<EdjOrders>()
                .eq(EdjOrders::getId, orderId)
                .eq(EdjOrders::getOrdersStatus, EdjOrderStatus.DISPATCHING)
                .set(EdjOrders::getOrdersStatus, EdjOrderStatus.PENDING_SERVE);
        int update = ordersMapper.update(new EdjOrders(), ordersUpdateWrapper);
        if (update != 1) {
            // 不删除，中断执行，回滚
            throw new ServerErrorException();
        }

        // 删除抢单池数据
        int d1 = baseMapper.deleteById(orderId);
        if (d1 != 1) {
            // 不删除，中断执行，回滚
            throw new ServerErrorException();
        }
        // 删除派单池数据
        ordersDispatchMapper.deleteById(orderId);
    }
}