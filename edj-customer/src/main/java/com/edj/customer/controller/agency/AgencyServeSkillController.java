package com.edj.customer.controller.agency;

import com.edj.customer.domain.dto.ServeSkillUpsertDTO;
import com.edj.customer.domain.vo.ServeSkillCategoryVO;
import com.edj.customer.service.EdjServeSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构端 - 服务技能相关接口
 *
 * @author A.E.
 * @date 2024/11/20
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("agency/serve/skill")
@Tag(name = "机构端 - 服务技能相关接口")
public class AgencyServeSkillController {

    private final EdjServeSkillService serveSkillService;

    /**
     * 批量新增或修改服务技能
     */
    @PostMapping("batch")
    @Operation(summary = "批量新增或修改服务技能")
    public void batchUpsert(
            @RequestBody
            @Validated
            @NotNull
            List<@Valid ServeSkillUpsertDTO> serveSkillUpsertDTOList
    ) {
        serveSkillService.batchUpsert(serveSkillUpsertDTOList);
    }

    /**
     * 查询服务技能目录
     */
    @GetMapping("category")
    @Operation(summary = "查询服务技能目录")
    public List<ServeSkillCategoryVO> category() {
        return serveSkillService.category();
    }
}
