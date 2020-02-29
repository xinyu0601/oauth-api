package com.xin.oauth.models.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-02-28 17:42
 * @class Authorize Request Body
 */
@Data
@Builder
public class AuthorizeRequestBody {

    private String userName;

    private String password;

    private String scope;

    private String status;

    private String smsCode;

    private String appKey;

    private String appSecret;
}
