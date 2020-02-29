package com.xin.oauth.service;


import com.xin.oauth.models.bo.AppBO;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 14:49
 * @class AppEntity service interface
 */

public interface AppService {

    boolean verifyByAppKeyAndAppSecret(String appKey, String appSecret);

    AppBO findByAppKeyAndAppSecret(String appKey, String appSecret);

}
