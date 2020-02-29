package com.xin.oauth.exceptions;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 23:46
 * @class App相关的Exception
 */
public class AppException extends RuntimeException {

    public AppException(String msg) {
        super(msg);
    }

    public AppException(String msg, Throwable e) {
        super(msg, e);
    }
}
