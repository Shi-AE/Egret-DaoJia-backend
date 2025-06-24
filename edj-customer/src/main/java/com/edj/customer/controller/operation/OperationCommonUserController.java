package com.edj.customer.controller.operation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.domain.dto.PageQueryDTO;
import com.edj.customer.domain.entity.EdjWorkerCertification;
import com.edj.customer.mapper.EdjWorkerCertificationMapper;
import com.edj.mysql.utils.PageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运营端 - 普通用户相关接口 todo 演示模拟数据
 *
 * @author A.E.
 * @date 2025/5/16
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("operation/user")
@Tag(name = "运营端 - 普通用户相关接口")
public class OperationCommonUserController {

    private final EdjWorkerCertificationMapper workerCertificationMapper;

    @GetMapping("page")
    @Operation(summary = "服务人员分页查询")
    public PageResult<EdjWorkerCertification> pageQueryWorker(PageQueryDTO pageQueryDTO) {
        Page<EdjWorkerCertification> page = PageUtils.parsePageQuery(pageQueryDTO);
        Page<EdjWorkerCertification> edjWorkerCertificationPage = workerCertificationMapper.selectPage(page, new LambdaQueryWrapper<>());
        return PageUtils.toPage(edjWorkerCertificationPage, EdjWorkerCertification.class);
    }
}
