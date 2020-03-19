package com.xin.oauth.utils.token;

import com.xin.oauth.exceptions.TokenException;
import com.xin.oauth.utils.DateUtils;
import com.xin.oauth.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


/**
 * @author xinyu.huang02
 * @date 2020-02-29 16:28
 * @class Refresh NeedToken Generator
 */
@Component
@Slf4j
public class RefreshTokenGenerator {

    private static final String TOKEN_FORMAT = "1.%s";

    public String generate(String appKey, String appSecret) {
        if (StringUtils.isBlank(appKey) || StringUtils.isBlank(appSecret))
            throw new TokenException("App Key or app secret is blank");
        String temp = appKey + appSecret + DateUtils.currentTimeInMillis();
        String accessToken = String.format(TOKEN_FORMAT, EncryptUtils.sha1Hex(temp));
        log.info("Generate new access token :" + accessToken);
        return accessToken;
    }
}
