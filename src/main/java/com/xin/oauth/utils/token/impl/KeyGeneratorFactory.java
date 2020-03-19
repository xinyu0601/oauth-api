package com.xin.oauth.utils.token.impl;

import com.xin.oauth.enums.KeyTypeEnum;
import com.xin.oauth.utils.token.KeyGenerator;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xinyu.huang02
 * @date 2020-03-19 19:47
 * @class key 生成工厂
 */

@Slf4j
public class KeyGeneratorFactory {

    private static final Map<KeyTypeEnum, KeyGenerator> keyGenerators = new HashMap<>();

    static {
        keyGenerators.put(KeyTypeEnum.accessToken, new AccessTokenGenerator());
        keyGenerators.put(KeyTypeEnum.appKey, new AppKeyGenerator());
        keyGenerators.put(KeyTypeEnum.appSecret, new AppSecretGenerator());
        keyGenerators.put(KeyTypeEnum.refreshToken, new RefreshTokenGenerator());
        keyGenerators.put(KeyTypeEnum.ticket, new TicketGenerator());
    }

    /**
     * 工厂方法创建keyGenerator
     *
     * @param type
     * @return
     */
    public static KeyGenerator createKeyGenerator(KeyTypeEnum type) {
        return keyGenerators.get(type);
    }

}
