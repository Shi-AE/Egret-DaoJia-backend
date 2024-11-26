package com.edj.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.api.api.user.UserApi;
import com.edj.common.domain.PageResult;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.*;
import com.edj.customer.domain.dto.AgencyCertificationAuditApplyDTO;
import com.edj.customer.domain.dto.AgencyCertificationAuditPageDTO;
import com.edj.customer.domain.dto.CertificationAuditDTO;
import com.edj.customer.domain.entity.EdjAgencyCertification;
import com.edj.customer.domain.entity.EdjAgencyCertificationAudit;
import com.edj.customer.domain.vo.AgencyCertificationAuditPageVO;
import com.edj.customer.domain.vo.RejectReasonVO;
import com.edj.customer.enums.EdjAuditStatus;
import com.edj.customer.enums.EdjCertificationStatus;
import com.edj.customer.mapper.EdjAgencyCertificationAuditMapper;
import com.edj.customer.mapper.EdjAgencyCertificationMapper;
import com.edj.customer.service.EdjAgencyCertificationAuditService;
import com.edj.mysql.utils.PageUtils;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 针对表【edj_agency_certification_audit(机构认证审核表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
@RequiredArgsConstructor
public class EdjAgencyCertificationAuditServiceImpl extends MPJBaseServiceImpl<EdjAgencyCertificationAuditMapper, EdjAgencyCertificationAudit> implements EdjAgencyCertificationAuditService {

    private final UserApi userApi;

    private final EdjAgencyCertificationMapper agencyCertificationMapper;

    @Override
    @Transactional
    public void applyCertification(AgencyCertificationAuditApplyDTO agencyCertificationAuditApplyDTO) {

        // 获取用户id
        Long userId = SecurityUtils.getUserId();

        // 新增审核
        EdjAgencyCertificationAudit workerCertificationAudit = BeanUtils.toBean(agencyCertificationAuditApplyDTO, EdjAgencyCertificationAudit.class);
        workerCertificationAudit.setEdjServeProviderId(userId);
        baseMapper.insert(workerCertificationAudit);

        // 查询认证记录
        LambdaQueryWrapper<EdjAgencyCertification> wrapper = new LambdaQueryWrapper<EdjAgencyCertification>()
                .select(EdjAgencyCertification::getCertificationStatus)
                .eq(EdjAgencyCertification::getId, userId);
        EdjAgencyCertification agencyCertification = agencyCertificationMapper.selectOne(wrapper);

        // 记录认证
        if (ObjectUtils.isNull(agencyCertification)) {
            // 新增认证记录
            agencyCertificationMapper.insert(EdjAgencyCertification
                    .builder()
                    .id(userId)
                    .certificationStatus((Integer) EnumUtils.value(EdjCertificationStatus.IN_PROGRESS))
                    .build()
            );
            return;
        }

        if (EnumUtils.ne(EdjCertificationStatus.IN_PROGRESS, agencyCertification.getCertificationStatus())) {
            // 修改认证状态
            agencyCertification.setId(userId);
            agencyCertification.setCertificationStatus((Integer) EnumUtils.value(EdjCertificationStatus.IN_PROGRESS));
            agencyCertificationMapper.updateById(agencyCertification);
        }
    }

    @Override
    public RejectReasonVO getLastRejectReason() {

        // 获取用户id
        Long userId = SecurityUtils.getUserId();

        LambdaQueryWrapper<EdjAgencyCertificationAudit> wrapper = new LambdaQueryWrapper<EdjAgencyCertificationAudit>()
                .select(EdjAgencyCertificationAudit::getRejectReason)
                .eq(EdjAgencyCertificationAudit::getEdjServeProviderId, userId)
                .orderByDesc(EdjAgencyCertificationAudit::getId)
                .last("limit 1");

        EdjAgencyCertificationAudit agencyCertificationAudit = baseMapper.selectOne(wrapper);
        if (ObjectUtils.isNull(agencyCertificationAudit)) {
            return null;
        }
        return new RejectReasonVO(agencyCertificationAudit.getRejectReason());
    }

    @Override
    public PageResult<AgencyCertificationAuditPageVO> page(AgencyCertificationAuditPageDTO agencyCertificationAuditPageDTO) {
        Page<EdjAgencyCertificationAudit> page = PageUtils.parsePageQuery(agencyCertificationAuditPageDTO);

        String name = agencyCertificationAuditPageDTO.getName();
        String legalPersonName = agencyCertificationAuditPageDTO.getLegalPersonName();
        Integer auditStatus = agencyCertificationAuditPageDTO.getAuditStatus();
        Integer certificationStatus = agencyCertificationAuditPageDTO.getCertificationStatus();

        LambdaQueryWrapper<EdjAgencyCertificationAudit> wrapper = new LambdaQueryWrapper<EdjAgencyCertificationAudit>()
                .likeRight(StringUtils.isNotBlank(name), EdjAgencyCertificationAudit::getName, name)
                .likeRight(StringUtils.isNotBlank(legalPersonName), EdjAgencyCertificationAudit::getLegalPersonName, legalPersonName)
                .eq(ObjectUtils.isNotNull(auditStatus), EdjAgencyCertificationAudit::getAuditStatus, auditStatus)
                .eq(ObjectUtils.isNotNull(certificationStatus), EdjAgencyCertificationAudit::getCertificationStatus, certificationStatus);

        Page<EdjAgencyCertificationAudit> agencyCertificationAuditPage = baseMapper.selectPage(page, wrapper);
        return PageUtils.toPage(agencyCertificationAuditPage, AgencyCertificationAuditPageVO.class);
    }

    @Override
    @Transactional
    public void auditCertification(Long id, CertificationAuditDTO certificationAuditDTO) {
        // 检查申请状态
        EdjAgencyCertificationAudit agencyCertificationAudit = baseMapper.selectById(id);
        if (ObjectUtils.isNull(agencyCertificationAudit)) {
            throw new BadRequestException("审核信息不存在");
        }

        if (EnumUtils.eq(EdjCertificationStatus.SUCCESS, agencyCertificationAudit.getCertificationStatus())) {
            throw new BadRequestException("申请已认证成功");
        }

        // 获取审核人信息
        Long userId = SecurityUtils.getUserId();
        String nickname = SecurityUtils.getNickname();

        // 获取审核结果
        Integer certificationStatus = certificationAuditDTO.getCertificationStatus();
        String rejectReason = certificationAuditDTO.getRejectReason();

        // 获取当前时刻
        LocalDateTime now = LocalDateTime.now();

        // 更新申请记录
        Runnable task1 = () -> {
            LambdaUpdateWrapper<EdjAgencyCertificationAudit> workerCertificationAuditUpdateWrapper = new LambdaUpdateWrapper<EdjAgencyCertificationAudit>()
                    .eq(EdjAgencyCertificationAudit::getId, id)
                    .set(EdjAgencyCertificationAudit::getAuditStatus, EdjAuditStatus.REVIEWED)
                    .set(EdjAgencyCertificationAudit::getAuditId, userId)
                    .set(EdjAgencyCertificationAudit::getAuditName, nickname)
                    .set(EdjAgencyCertificationAudit::getAuditTime, now)
                    .set(EdjAgencyCertificationAudit::getCertificationStatus, certificationStatus)
                    .set(StringUtils.isNotBlank(rejectReason), EdjAgencyCertificationAudit::getRejectReason, rejectReason);
            baseMapper.update(workerCertificationAuditUpdateWrapper);
        };

        // 更新认证信息
        EdjAgencyCertification agencyCertification = new EdjAgencyCertification();
        Long serveProviderId = agencyCertificationAudit.getEdjServeProviderId();
        String serveProviderName = agencyCertificationAudit.getName();
        agencyCertification.setId(serveProviderId);
        agencyCertification.setCertificationStatus(certificationStatus);

        if (EnumUtils.eq(EdjCertificationStatus.SUCCESS, certificationStatus)) {
            // 认证成功，同步认证信息
            agencyCertification.setName(serveProviderName);
            agencyCertification.setIdNumber(agencyCertificationAudit.getIdNumber());
            agencyCertification.setLegalPersonName(agencyCertificationAudit.getLegalPersonName());
            agencyCertification.setLegalPersonIdCardNo(agencyCertificationAudit.getLegalPersonIdCardNo());
            agencyCertification.setBusinessLicense(agencyCertificationAudit.getBusinessLicense());
            agencyCertification.setCertificationTime(now);
        }

        // 更新认证信息
        Runnable task2 = () -> agencyCertificationMapper.updateById(agencyCertification);

        // 处理异步
        AsyncUtils.runAsyncTransaction(List.of(task1, task2));

        // 修改用户名
        // 由于远程调用更新存在事务，必须保证调用后不存在可能出现异常的代码
        userApi.updateNameById(serveProviderId, serveProviderName);
    }
}