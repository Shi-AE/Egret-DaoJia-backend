package com.edj.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件上传响应值
 *
 * @author A.E.
 * @date 2024/9/20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageUploadResDTO {
    /**
     * 文件地址
     */
    private String url;
}