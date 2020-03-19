package com.xin.oauth.annotation;

import java.lang.annotation.*;

/**
 * @author xinyu.huang02
 * @date 2020-03-01 13:05
 * @class Token验证注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedToken {

}
