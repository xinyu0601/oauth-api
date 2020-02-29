package com.xin.oauth.exceptions;

/**
 * @author xinyu.huang02
 * @date 2020-02-28 11:36
 * @class 短信相关的异常
 */
public class SMSException extends RuntimeException {

    public SMSException(String msg) {
        super(msg);
    }

    public SMSException(String msg, Throwable e) {
        super(msg, e);
    }
}
