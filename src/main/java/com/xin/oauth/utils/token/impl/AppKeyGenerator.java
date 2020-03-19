package com.xin.oauth.utils.token.impl;

import com.xin.oauth.utils.EncryptUtils;
import com.xin.oauth.utils.token.AbstractKeyGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xinyu.huang02
 * @date 2020-02-29 12:37
 * @class App key 生成器
 */

@Slf4j
public class AppKeyGenerator extends AbstractKeyGenerator {

    private static final int APP_KEY_SIZE = 24;

    public String generate() {
        return EncryptUtils.generateRandomStr(APP_KEY_SIZE);
    }

}
