package com.xin.oauth.service;


import com.xin.oauth.models.bo.AppBO;

import java.util.List;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 14:49
 * @class AppEntity service interface
 */

public interface AppService {

    AppBO registerApp(AppBO appBO);

    void removeApp(Long appId);

    void removeByAppName(String appName);

    AppBO updateApp(Long appId, AppBO appBO);

    List<AppBO> all(Long userId);

    boolean verifyByAppKeyAndAppSecret(String appKey, String appSecret);

    AppBO findByAppKeyAndAppSecret(String appKey, String appSecret);

}
