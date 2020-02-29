package com.xin.oauth.models.request;

import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 21:16
 * @class Refresh Token Request Body
 */

@Data
public class RefreshTokenRequestBody {

    private String refreshToken;

    private String tokenId;



}
