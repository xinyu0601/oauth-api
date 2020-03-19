package com.xin.oauth.annotation;

import java.lang.annotation.*;

/**
 * @author xinyu.huang02
 * @date 2020-03-19 11:14
 * @class 保证幂等性
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ApiIdempotent {

    /**
     * 持有锁的时间
     */
    long keepMills() default 30000;

    /**
     * 重试间隔时间
     */
    long sleepMills() default 200;

    /**
     * 重试次数
     */
    int retryTimes() default 5;

    // 无法获取令牌返回提示信息 默认值可以自行修改
    String msg() default "请勿重复请求";

}
