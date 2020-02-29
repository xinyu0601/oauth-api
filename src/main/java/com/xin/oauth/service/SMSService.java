package com.xin.oauth.service;

/**
 * @author xinyu.huang02
 * @date 2020-02-28 11:29
 * @class 短信相关业务逻辑操作接口
 */
public interface SMSService {

    boolean verifySMSCode(String code);

}
