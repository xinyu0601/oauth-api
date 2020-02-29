package com.xin.oauth.exceptions;

/**
 * @author xinyu.huang02
 * @date 2020-02-28 11:26
 * @class 用户登录相关异常
 */
public class UserLoginException extends RuntimeException {

    public UserLoginException(String msg) {
        super(msg);
    }

    public UserLoginException(String msg, Throwable e) {
        super(msg, e);
    }

}
