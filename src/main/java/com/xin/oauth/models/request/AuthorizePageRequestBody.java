package com.xin.oauth.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 00:03
 * @class Authorize Request Body
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizePageRequestBody {

    private String appKey;

    private String appSecret;

    private String scope;

    private String status;
}
