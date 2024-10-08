package com.edj.user.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "生成token")
public class UserTokenVO {

    /**
     * 认证token
     */
    @Schema(description = "认证token")
    private String accessToken;

    /**
     * 刷新token
     */
    @Schema(description = "刷新token")
    private String refreshToken;
}
