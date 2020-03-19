package com.xin.oauth.utils.token;

import com.xin.oauth.utils.token.impl.AppSecretGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author xinyu.huang02
 * @date 2020-03-02 10:44
 * @class App secret generator test case
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppSecretGeneratorTest {

    private static final Logger log = LoggerFactory.getLogger(AppSecretGeneratorTest.class);

    @Autowired
    private AppSecretGenerator appSecretGenerator;

    @Test
    public void testGenerate() {
        String appSecret = appSecretGenerator.generate();
        log.info("App secret = " + appSecret);
        assertEquals(32, appSecret.length());
    }

}
