package com.xin.oauth.models.bo;

import com.xin.oauth.models.entity.AuthRefreshTokenEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-02-28 17:28
 * @class Auth Refresh Token BO
 */
@Data
@Builder
public class AuthRefreshTokenBO {

    private Long id;

    private Long accessTokenId;

    private String refreshToken;

    private Long expiresAt;

    public static AuthRefreshTokenBO fromEntity(AuthRefreshTokenEntity authRefreshTokenEntity) {
        return AuthRefreshTokenBO.builder()
                .accessTokenId(authRefreshTokenEntity.getAccessTokenId())
                .refreshToken(authRefreshTokenEntity.getRefreshToken())
                .expiresAt(authRefreshTokenEntity.getExpiresAt())
                .build();
    }
}
