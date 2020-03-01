package com.xin.oauth.models.request;

import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-02-29 17:30
 * @class Logout Request Body
 */

@Data
public class LogoutRequestBody {

    private String ticket;

    private String accessToken;

}
