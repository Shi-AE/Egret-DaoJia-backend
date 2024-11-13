package com.edj.customer.controller.worker;

import com.edj.customer.domain.dto.ServeSkillUpsertDTO;
import com.edj.customer.service.EdjServeSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 服务端 - 服务技能相关接口
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("worker/serve/skill")
@Tag(name = "服务端 - 服务技能相关接口")
public class WorkerServeSkillController {

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
}
