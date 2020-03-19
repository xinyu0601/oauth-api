package com.xin.oauth.utils.token;

/**
 * @author xinyu.huang02
 * @date 2020-03-20 00:10
 * @class Id Generator接口
 */
public interface KeyGenerator {

    String generate();

    String generate(String appKey, String appSecret);
}
