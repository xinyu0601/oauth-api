package com.xin.oauth.utils.token;

/**
 * @author xinyu.huang02
 * @date 2020-03-19 19:49
 * @class Id Generator 接口
 */
public interface IdGenerator {

    String generate();

    String generate(String appkey, String appSecret);
}
