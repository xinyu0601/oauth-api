package com.xin.oauth.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author xinyu.huang02
 * @date 2020-03-19 17:05
 * @class 限流注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface NeedRateLimiter {

    //以固定数值往令牌桶添加令牌
    double permitsPerSecond() default 1000;

    long timeout() default 500;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    // 无法获取令牌返回提示信息 默认值可以自行修改
    String msg() default "系统繁忙,请稍后再试.";
}
