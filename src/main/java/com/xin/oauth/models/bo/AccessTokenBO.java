package com.xin.oauth.models.bo;

import lombok.Builder;
import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 22:54
 * @class TODO
 */
@Data
@Builder
public class AccessTokenBO {

    private String appId;

    private String appSecret;

    private String accessToken;

    private Long expireAt;
}
