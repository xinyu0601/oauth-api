package com.xin.oauth.utils.token.impl;

import com.xin.oauth.utils.EncryptUtils;
import com.xin.oauth.utils.token.AbstractKeyGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xinyu.huang02
 * @date 2020-02-29 12:37
 * @class App Secret Generator
 */

@Slf4j
public class AppSecretGenerator extends AbstractKeyGenerator {

    private static final int APP_SECRET_SIZE = 32;

    public String generate() {
        return EncryptUtils.generateRandomStr(APP_SECRET_SIZE);
    }

}
