package com.xin.oauth.utils.token;

import com.xin.oauth.exceptions.TokenException;
import com.xin.oauth.utils.token.impl.AppKeyGenerator;
import com.xin.oauth.utils.token.impl.AppSecretGenerator;
import com.xin.oauth.utils.token.impl.RefreshTokenGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xinyu.huang02
 * @date 2020-03-02 10:45
 * @class Refresh token generate test case
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RefreshTokenGeneratorTest {

    private static final Logger log = LoggerFactory.getLogger(RefreshTokenGenerator.class);

    @Autowired
    private RefreshTokenGenerator refreshTokenGenerator;

    @Autowired
    private AppKeyGenerator appKeyGenerator;

    @Autowired
    private AppSecretGenerator appSecretGenerator;

    @Test
    public void testGenerate() {
        String appKey = appKeyGenerator.generate();
        String appSecret = appSecretGenerator.generate();
        String accessToken = refreshTokenGenerator.generate(appKey, appSecret);
        log.info(String.format("app key = %s; app secret = %s; access token = %s", appKey, appSecret, accessToken));
    }

    @Test
    public void testGenerateIfAppKeyIsNull() {
        String appSecret = appSecretGenerator.generate();
        try {
            refreshTokenGenerator.generate(null, appSecret);
        } catch (TokenException e) {
            log.error("Generate refresh token error", e);
        }
    }

    @Test
    public void testGenerateIfAppKeyIsEmpty() {
        String appSecret = appSecretGenerator.generate();
        try {
            refreshTokenGenerator.generate("", appSecret);
        } catch (TokenException e) {
            log.error("Generate refresh token error", e);
        }
    }


    @Test
    public void testGenerateIfAppSecretIsNull() {
        String appKey = appSecretGenerator.generate();
        try {
            refreshTokenGenerator.generate(appKey, null);
        } catch (TokenException e) {
            log.error("Generate refresh token error", e);
        }
    }


    @Test
    public void testGenerateIfAppSecretIsEmpty() {
        String appKey = appSecretGenerator.generate();
        try {
            refreshTokenGenerator.generate(appKey, "");
        } catch (TokenException e) {
            log.error("Generate refresh token error", e);
        }
    }
}
