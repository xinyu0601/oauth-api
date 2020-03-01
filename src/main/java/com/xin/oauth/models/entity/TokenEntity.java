package com.xin.oauth.models.entity;

import com.xin.oauth.models.bo.AccessTokenBO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 21:31
 * @class Access Token Entity
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntity {

    private Long id;

    private String accessToken;

    private String refreshToken;

    private String appKey;

    private String appSecret;

    private Long expiresIn;

    private String grantType;

    private String scope;

    public static TokenEntity fromBO(AccessTokenBO accessTokenBO) {
        return TokenEntity.builder()
                .accessToken(accessTokenBO.getAccessToken())
                .appKey(accessTokenBO.getAppKey())
                .appSecret(accessTokenBO.getAppSecret())
                .expiresIn(accessTokenBO.getExpiresIn())
                .refreshToken(accessTokenBO.getRefreshToken())
                .scope(accessTokenBO.getScope()).build();
    }


}
