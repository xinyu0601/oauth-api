package com.xin.oauth.utils.token;


import com.xin.oauth.utils.DateUtils;
import com.xin.oauth.utils.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 14:47
 * @class Access Token 生成器
 */

@Component
public class AccessTokenGenerator {

    private static final Logger log = LoggerFactory.getLogger(AccessTokenGenerator.class);

    private static final String TOKEN_FORMAT = "1.%s";

    public String generate(String appId, String appSecret) {
        String temp = appId + DateUtils.currentTimeInMillis();
        String accessToken = String.format(TOKEN_FORMAT, EncryptUtils.sha1Hex(temp));
        log.info("Generate new access token :" + accessToken);
        return accessToken;
    }

}
