package com.xin.oauth.utils.token;

import com.xin.oauth.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xinyu.huang02
 * @date 2020-02-29 12:37
 * @class App key 生成器
 */

@Component
@Slf4j
public class AppKeyGenerator {

    private static final int APP_KEY_SIZE = 24;

    public String generate() {
        return EncryptUtils.generateRandomStr(APP_KEY_SIZE);
    }

}
