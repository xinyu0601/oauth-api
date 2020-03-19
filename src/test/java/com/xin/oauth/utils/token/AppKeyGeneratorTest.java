package com.xin.oauth.utils.token;

import com.xin.oauth.utils.token.impl.AppKeyGenerator;
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
 * @class App key generator test case
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppKeyGeneratorTest {

    private static final Logger log = LoggerFactory.getLogger(AppKeyGeneratorTest.class);

    @Autowired
    private AppKeyGenerator appKeyGenerator;

    @Test
    public void generate() {
        String appKey = appKeyGenerator.generate();
        log.info("App key = " + appKey);
        assertEquals(24, appKey.length());
    }

}
