package com.xin.oauth.utils.token;

/**
 * @author xinyu.huang02
 * @date 2020-03-19 19:49
 * @class Id Generator 抽象类
 */
public abstract class AbstractKeyGenerator implements KeyGenerator {

    /**
     * 默认生成key的方式
     *
     * @return
     */
    @Override
    public String generate() {
        return null;
    }

    /**
     * 根据AppKey和AppSecret生成Key
     *
     * @param appKey
     * @param appSecret
     * @return
     */
    @Override
    public String generate(String appKey, String appSecret) {
        return null;
    }

}
