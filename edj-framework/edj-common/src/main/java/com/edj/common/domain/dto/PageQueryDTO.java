package com.edj.common.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Schema(description = "分页查询数据")
public class PageQueryDTO {
    @Schema(description = "页码数")
    private Integer pageNo = 1;
    @Schema(description = "每页条数")
    private Integer pageSize = 10;
    @Schema(description = "排序字段1")
    private String orderBy1;
    @Schema(description = "排序字段1是否升序")
    private Boolean isAsc1 = false;

    @Schema(description = "排序字段2，排序顺序排在排序字段1后边，如果排序字段1未设置，该字段也可以排序")
    private String orderBy2;
    @Schema(description = "排序字段2是否升序")
    private Boolean isAsc2 = false;

    /**
     * 计算起始条数
     *
     * @return 起始条数
     */
    public Integer calFrom() {
        return (pageNo - 1) * pageSize;
    }

    @AllArgsConstructor
    @Getter
    public static class OrderBy {
        private String orderBy;
        private Boolean isAsc;
    }
}
