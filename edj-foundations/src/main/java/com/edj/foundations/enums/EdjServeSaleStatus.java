package com.edj.foundations.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EdjServeSaleStatus {

    DRAFT(0, "草稿"),
    UNPUBLISHED(1, "下架"),
    PUBLISHED(2, "上架");

    @EnumValue
    private final Integer value;
    private final String describe;
}
