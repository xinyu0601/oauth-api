package com.xin.oauth.mapper;

import com.xin.oauth.OauthApplicationTests;
import com.xin.oauth.models.entity.AppEntity;
import com.xin.oauth.utils.DateUtils;
import com.xin.oauth.utils.token.impl.AppKeyGenerator;
import com.xin.oauth.utils.token.impl.AppSecretGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 22:12
 * @class App Mapper test Case
 */
@FixMethodOrder(MethodSorters.JVM)
public class AppMapperTest extends OauthApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(AppMapperTest.class);

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private AppKeyGenerator appKeyGenerator;

    @Autowired
    private AppSecretGenerator appSecretGenerator;

    @Before
    public void setUp() {

    }

    @Test
    public void registerApp() {
        String appKey = appKeyGenerator.generate();
        String appSecret = appSecretGenerator.generate();
        logger.info("Register app");
        AppEntity app = AppEntity.builder()
                .appKey(appKey)
                .appName("test")
                .appSecret(appSecret)
                .callbackUrl("http://www.baidu.com")
                .isOpen(true)
                .description("test")
                .createTime(DateUtils.currentTimeStr())
                .userId(1L).build();
        appMapper.insert(app);
        AppEntity insertApp = appMapper.selectByAppKey(appKey);
        assertNotNull("Insert app failed", insertApp);
        assertEquals("test", insertApp.getAppName());
    }

    @Test
    public void getAppByAppKey() {
        logger.info("Get app");
        AppEntity appEntity = appMapper.selectByAppName("test");
        assertNotNull(appEntity);
        appEntity = appMapper.selectByAppKey(appEntity.getAppKey());
        assertNotNull(appEntity);
    }

    @Test
    public void updateAppByAppKey() {
        logger.info("Update app");
        final String updateCallbackUrl = "http://www.sohu.com";
        AppEntity appEntity = appMapper.selectByAppName("test");
        assertNotNull(appEntity);
        appEntity.setCallbackUrl(updateCallbackUrl);
        appMapper.updateByAppKey(appEntity);
        assertNotNull(appEntity.getAppKey());
        AppEntity updatedApp = appMapper.selectByAppKey(appEntity.getAppKey());
        assertNotNull("Update app failed", updatedApp);
        assertEquals(updateCallbackUrl, updatedApp.getCallbackUrl());
    }

    @Test
    public void deleteByAppKey() {
        AppEntity appEntity = appMapper.selectByAppName("test");
        assertNotNull(appEntity);
        assertNotNull(appEntity.getAppKey());
        appMapper.deleteByAppKey(appEntity.getAppKey());
        AppEntity deletedApp = appMapper.selectByAppKey(appEntity.getAppKey());
        assertNull(deletedApp);
    }


    @After
    public void end() {

    }

}
