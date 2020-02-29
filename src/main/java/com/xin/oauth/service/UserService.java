package com.xin.oauth.service;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 16:36
 * @class 用户业务逻辑类
 */
public interface UserService {

    boolean verifyUser(String userName, String password);

}
