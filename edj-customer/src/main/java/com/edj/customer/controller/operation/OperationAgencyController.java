package com.edj.customer.controller.operation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.domain.dto.PageQueryDTO;
import com.edj.customer.domain.entity.EdjAgencyCertification;
import com.edj.customer.mapper.EdjAgencyCertificationMapper;
import com.edj.mysql.utils.PageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运营端 - 机构相关接口 todo 演示模拟数据
 *
 * @author A.E.
 * @date 2025/5/16
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("operation/Agency")
@Tag(name = "运营端 - 机构相关接口")
public class OperationAgencyController {

    private final EdjAgencyCertificationMapper agencyCertificationMapper;

    @GetMapping("page")
    @Operation(summary = "机构分页查询")
    public PageResult<EdjAgencyCertification> pageQueryAgency(PageQueryDTO pageQueryDTO) {
        Page<EdjAgencyCertification> page = PageUtils.parsePageQuery(pageQueryDTO);
        Page<EdjAgencyCertification> edjAgencyCertificationAuditPage = agencyCertificationMapper.selectPage(page, new LambdaQueryWrapper<>());
        return PageUtils.toPage(edjAgencyCertificationAuditPage, EdjAgencyCertification.class);
    }
}
