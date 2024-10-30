package com.edj.api.api.publics.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author A.E.
 * @date 2024/10/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenIdDTO {
    private String openId;
}
