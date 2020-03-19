package com.xin.oauth.service;

import com.xin.oauth.OauthApplicationTests;
import com.xin.oauth.models.bo.AppBO;
import com.xin.oauth.utils.DateUtils;
import com.xin.oauth.utils.token.impl.AppKeyGenerator;
import com.xin.oauth.utils.token.impl.AppSecretGenerator;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

import java.util.List;


/**
 * @author xinyu.huang02
 * @date 2020-02-27 14:38
 * @class App service test case
 */

@FixMethodOrder(MethodSorters.JVM)
public class AppServiceTest extends OauthApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(AppServiceTest.class);

    @Autowired
    private AppService appService;

    @Autowired
    private AppKeyGenerator appKeyGenerator;

    @Autowired
    private AppSecretGenerator appSecretGenerator;


    @Before
    public void setUp() {
        log.info("Start test app service");
    }

    @Test
    public void testRegisterApp() {
        AppBO appBO = AppBO.builder()
                .appName("test_app")
                .userId(1L)
                .isOpen(true)
                .description("test")
                .callbackUrl("http://www.baidu.com")
                .createTime(DateUtils.currentTimeStr())
                .build();
        log.info(String.format("app bo = %s", appBO));
        appBO = appService.registerApp(appBO);
        assertNotNull("register app failed", appBO);
        log.info(String.format("app = %s", appBO.toString()));
    }


    @Test
    public void testUpdateApp() {
        List<AppBO> appBOList = appService.all(1L);
        for (AppBO app : appBOList) {
            AppBO appBO = AppBO.builder()
                    .appName("test_app")
                    .description("test222")
                    .callbackUrl("http://www.souhu.com")
                    .build();
            appBO = appService.updateApp(app.getAppId(), appBO);
            log.info(String.format("app = %s", appBO.toString()));
        }
    }

    @Test
    public void testGetAllApp() {
        List<AppBO> appBOList = appService.all(1L);
        log.info("appBOList size = " + appBOList.size());
    }


    @Test
    public void testVerifyByAppKeyAndAppSecret() {
        List<AppBO> appBOList = appService.all(1L);
        for (AppBO app : appBOList) {
            boolean isValid = appService.verifyByAppKeyAndAppSecret(app.getAppKey(), app.getAppSecret());
            log.info(String.format("App key = %s, App secret = %s, isValid = %s", app.getAppKey(), app.getAppSecret(), isValid));
        }
    }

    @Test
    public void testFindByAppKeyAndAppSecret() {
        List<AppBO> appBOList = appService.all(1L);
        for (AppBO app : appBOList) {
            AppBO findApp = appService.findByAppKeyAndAppSecret(app.getAppKey(), app.getAppSecret());
            log.info("find app = " + findApp.toString());
        }
    }

    @Test
    public void testRemoveApp() {
        final String appName = "test_app";
        appService.removeByAppName(appName);
    }


    @After
    public void end() {

    }

}
