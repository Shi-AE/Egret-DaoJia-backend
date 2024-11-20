package com.edj.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.api.api.user.UserApi;
import com.edj.common.domain.PageResult;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.*;
import com.edj.customer.domain.dto.CertificationAuditDTO;
import com.edj.customer.domain.dto.WorkerCertificationAuditApplyDTO;
import com.edj.customer.domain.dto.WorkerCertificationAuditPageDTO;
import com.edj.customer.domain.entity.EdjWorkerCertification;
import com.edj.customer.domain.entity.EdjWorkerCertificationAudit;
import com.edj.customer.domain.vo.RejectReasonVO;
import com.edj.customer.domain.vo.WorkerCertificationAuditPageVO;
import com.edj.customer.enums.EdjAuditStatus;
import com.edj.customer.enums.EdjCertificationStatus;
import com.edj.customer.mapper.EdjWorkerCertificationAuditMapper;
import com.edj.customer.mapper.EdjWorkerCertificationMapper;
import com.edj.customer.service.EdjWorkerCertificationAuditService;
import com.edj.mysql.utils.PageUtils;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * 针对表【edj_worker_certification_audit(服务人员认证审核表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
@RequiredArgsConstructor
public class EdjWorkerCertificationAuditServiceImpl extends MPJBaseServiceImpl<EdjWorkerCertificationAuditMapper, EdjWorkerCertificationAudit> implements EdjWorkerCertificationAuditService {

    private final EdjWorkerCertificationMapper workerCertificationMapper;

    private final UserApi userApi;

    @Override
    @Transactional
    public void applyCertification(WorkerCertificationAuditApplyDTO workerCertificationAuditApplyDTO) {

        Long userId = SecurityUtils.getUserId();

        // 新增审核
        EdjWorkerCertificationAudit workerCertificationAudit = BeanUtils.toBean(workerCertificationAuditApplyDTO, EdjWorkerCertificationAudit.class);
        workerCertificationAudit.setEdjServeProviderId(userId);
        baseMapper.insert(workerCertificationAudit);

        // 查询认证记录
        LambdaQueryWrapper<EdjWorkerCertification> wrapper = new LambdaQueryWrapper<EdjWorkerCertification>()
                .select(EdjWorkerCertification::getCertificationStatus)
                .eq(EdjWorkerCertification::getId, userId);
        EdjWorkerCertification workerCertification = workerCertificationMapper.selectOne(wrapper);

        // 记录认证
        if (ObjectUtils.isNull(workerCertification)) {
            // 新增认证记录
            workerCertificationMapper.insert(EdjWorkerCertification
                    .builder()
                    .id(userId)
                    .certificationStatus((Integer) EnumUtils.value(EdjCertificationStatus.IN_PROGRESS))
                    .build()
            );
            return;
        }

        if (EnumUtils.ne(EdjCertificationStatus.IN_PROGRESS, workerCertification.getCertificationStatus())) {
            // 修改认证状态
            workerCertification.setId(userId);
            workerCertification.setCertificationStatus((Integer) EnumUtils.value(EdjCertificationStatus.IN_PROGRESS));
            workerCertificationMapper.updateById(workerCertification);
        }
    }

    @Override
    public PageResult<WorkerCertificationAuditPageVO> page(WorkerCertificationAuditPageDTO workerCertificationAuditPageDTO) {
        Page<EdjWorkerCertificationAudit> page = PageUtils.parsePageQuery(workerCertificationAuditPageDTO);

        String name = workerCertificationAuditPageDTO.getName();
        String idCardNo = workerCertificationAuditPageDTO.getIdCardNo();
        Integer certificationStatus = workerCertificationAuditPageDTO.getCertificationStatus();
        Integer auditStatus = workerCertificationAuditPageDTO.getAuditStatus();

        LambdaQueryWrapper<EdjWorkerCertificationAudit> wrapper = new LambdaQueryWrapper<EdjWorkerCertificationAudit>()
                .likeRight(StringUtils.isNotBlank(name), EdjWorkerCertificationAudit::getName, name)
                .likeRight(StringUtils.isNotBlank(idCardNo), EdjWorkerCertificationAudit::getIdCardNo, idCardNo)
                .eq(ObjectUtils.isNotNull(certificationStatus), EdjWorkerCertificationAudit::getCertificationStatus, certificationStatus)
                .eq(ObjectUtils.isNotNull(auditStatus), EdjWorkerCertificationAudit::getAuditStatus, auditStatus);

        Page<EdjWorkerCertificationAudit> workerCertificationAuditPage = baseMapper.selectPage(page, wrapper);
        return PageUtils.toPage(workerCertificationAuditPage, WorkerCertificationAuditPageVO.class);
    }

    @Override
    @Transactional
    public void auditCertification(Long id, CertificationAuditDTO certificationAuditDTO) {
        // 检查申请状态
        EdjWorkerCertificationAudit workerCertificationAudit = baseMapper.selectById(id);
        if (ObjectUtils.isNull(workerCertificationAudit)) {
            throw new BadRequestException("审核信息不存在");
        }

        if (EnumUtils.eq(EdjCertificationStatus.SUCCESS, workerCertificationAudit.getCertificationStatus())) {
            throw new BadRequestException("申请已认证成功");
        }

        // 获取审核人信息
        Long userId = SecurityUtils.getUserId();
        String nickname = SecurityUtils.getNickname();

        // 获取审核结果
        Integer certificationStatus = certificationAuditDTO.getCertificationStatus();
        String rejectReason = certificationAuditDTO.getRejectReason();

        // 更新申请记录
        CompletableFuture<Void> future1 = AsyncUtils.runAsyncTransaction(() -> {
            LambdaUpdateWrapper<EdjWorkerCertificationAudit> workerCertificationAuditUpdateWrapper = new LambdaUpdateWrapper<EdjWorkerCertificationAudit>()
                    .eq(EdjWorkerCertificationAudit::getId, id)
                    .set(EdjWorkerCertificationAudit::getAuditStatus, EdjAuditStatus.REVIEWED)
                    .set(EdjWorkerCertificationAudit::getAuditId, userId)
                    .set(EdjWorkerCertificationAudit::getAuditName, nickname)
                    .set(EdjWorkerCertificationAudit::getAuditTime, LocalDateTime.now())
                    .set(EdjWorkerCertificationAudit::getCertificationStatus, certificationStatus)
                    .set(StringUtils.isNotBlank(rejectReason), EdjWorkerCertificationAudit::getRejectReason, rejectReason);
            baseMapper.update(workerCertificationAuditUpdateWrapper);
        });

        // 更新认证信息
        EdjWorkerCertification workerCertification = new EdjWorkerCertification();
        Long serveProviderId = workerCertificationAudit.getEdjServeProviderId();
        workerCertification.setId(serveProviderId);
        workerCertification.setCertificationStatus(certificationStatus);

        CompletableFuture<Void> future2 = AsyncUtils.runAsync(() -> {
        });
        if (EnumUtils.eq(EdjCertificationStatus.SUCCESS, certificationStatus)) {
            // 认证成功，同步认证信息
            String serveProviderName = workerCertificationAudit.getName();
            workerCertification.setName(serveProviderName);
            workerCertification.setIdCardNo(workerCertificationAudit.getIdCardNo());
            workerCertification.setFrontImg(workerCertificationAudit.getFrontImg());
            workerCertification.setBackImg(workerCertificationAudit.getBackImg());
            workerCertification.setCertificationMaterial(workerCertificationAudit.getCertificationMaterial());
            workerCertification.setCertificationTime(workerCertificationAudit.getAuditTime());

            // 修改用户名
            future2 = AsyncUtils.runAsyncTransaction(() -> userApi.updateNameById(serveProviderId, serveProviderName));
        }

        // 更新认证信息
        CompletableFuture<Void> future3 = AsyncUtils.runAsyncTransaction(() -> workerCertificationMapper.updateById(workerCertification));

        // 处理异步
        CompletableFuture.allOf(future1, future2, future3).join();
    }

    @Override
    public RejectReasonVO getLastRejectReason() {

        Long userId = SecurityUtils.getUserId();

        LambdaQueryWrapper<EdjWorkerCertificationAudit> wrapper = new LambdaQueryWrapper<EdjWorkerCertificationAudit>()
                .select(EdjWorkerCertificationAudit::getRejectReason)
                .eq(EdjWorkerCertificationAudit::getEdjServeProviderId, userId)
                .orderByDesc(EdjWorkerCertificationAudit::getId)
                .last("limit 1");

        EdjWorkerCertificationAudit workerCertificationAudit = baseMapper.selectOne(wrapper);
        if (ObjectUtils.isNull(workerCertificationAudit)) {
            return null;
        }
        return new RejectReasonVO(workerCertificationAudit.getRejectReason());
    }
}