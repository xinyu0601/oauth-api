package com.xin.oauth.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 00:03
 * @class Login Request Body
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestBody {

    private String userName;

    private String password;

    private String smsCode;

}
