package com.xin.oauth.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xinyu.huang02
 * @date 2020-03-05 10:44
 * @class 服务相关配置类
 */

@Component
@Data
public class AppConfigurer {

    @Value("${project.oauth.authorizePage}")
    private String oauthAuthorizePage;

}
