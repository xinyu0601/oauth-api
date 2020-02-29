package com.xin.oauth.exceptions;

/**
 * @author xinyu.huang02
 * @date 2020-02-28 00:25
 * @class Token相关异常处理
 */
public class TokenException extends RuntimeException {

    public TokenException(String msg) {
        super(msg);
    }

    public TokenException(String msg, Throwable e) {
        super(msg, e);
    }
}
