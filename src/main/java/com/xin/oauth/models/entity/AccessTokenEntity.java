package com.xin.oauth.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 21:31
 * @class Access Token Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenEntity {

    private String accessToken;

    private Long userId;

    private String userName;

    private Integer appId;

    private Long expiredIn;

    private String grantType;

    private String scope;


}
