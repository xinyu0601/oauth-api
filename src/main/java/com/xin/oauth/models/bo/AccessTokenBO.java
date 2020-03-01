package com.xin.oauth.models.bo;

import com.xin.oauth.models.entity.TokenEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 22:54
 * @class Access Token BO
 */
@Data
@Builder
public class AccessTokenBO {

    private String accessToken;

    private Long expiresIn;

    private String refreshToken;

    private String scope;

    private String appKey;

    private String appSecret;

    public static AccessTokenBO fromEntity(TokenEntity tokenEntity) {
        if (tokenEntity == null)
            return null;
        return AccessTokenBO.builder()
                .accessToken(tokenEntity.getAccessToken())
                .refreshToken(tokenEntity.getRefreshToken())
                .expiresIn(tokenEntity.getExpiresIn())
                .scope(tokenEntity.getScope())
                .appKey(tokenEntity.getAppKey())
                .appSecret(tokenEntity.getAppSecret()).build();
    }

}
