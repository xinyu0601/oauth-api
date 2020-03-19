package com.xin.oauth.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 00:22
 * @class Access NeedToken Request Body
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenRequestBody {

    private String ticket;

    private String scope;

    private String appKey;

    private String appSecret;
}
