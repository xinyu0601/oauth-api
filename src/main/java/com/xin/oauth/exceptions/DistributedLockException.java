package com.xin.oauth.exceptions;

/**
 * @author xinyu.huang02
 * @date 2020-03-19 14:36
 * @class 分布式锁相关异常
 */
public class DistributedLockException extends RuntimeException {

    public DistributedLockException(String msg) {
        super(msg);
    }

    public DistributedLockException(String msg, Throwable e) {
        super(msg, e);
    }

}
