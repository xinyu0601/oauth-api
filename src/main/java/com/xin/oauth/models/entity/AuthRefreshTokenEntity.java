package com.xin.oauth.models.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-02-28 17:23
 * @class Auth refresh token entity
 */

@Data
@Builder
public class AuthRefreshTokenEntity {

    private Long id;

    private Long accessTokenId;

    private String refreshToken;

    private Long expiresAt;


}
