package com.edj.common.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询数据")
public class PageQueryDTO {
    @Schema(description = "页码数", requiredMode = NOT_REQUIRED)
    private Integer pageNo = 1;
    @Schema(description = "每页条数", requiredMode = NOT_REQUIRED)
    private Integer pageSize = 10;

    @Schema(description = "排序项列表", requiredMode = NOT_REQUIRED)
    private List<@Valid OrderBy> orderByList;

    /**
     * 计算起始条数
     *
     * @return 起始条数
     */
    public Integer calFrom() {
        return (pageNo - 1) * pageSize;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "排序项")
    public static class OrderBy {

        @Schema(description = "排序字段")
        @NotBlank(message = "排序字段不能为空")
        private String orderBy;

        @Schema(description = "是否升序", requiredMode = NOT_REQUIRED)
        private Boolean isAsc = true;
    }
}
