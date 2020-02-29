package com.xin.oauth.mapper;

import com.xin.oauth.OauthApplicationTests;
import com.xin.oauth.models.entity.AppEntity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 22:12
 * @class App Mapper 测试用例
 */
public class AppMapperTest extends OauthApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(AppMapperTest.class);

    @Autowired
    private AppMapper appMapper;


    @Test
    public void registerApp() {

        logger.info("Register app");
        AppEntity app = AppEntity.builder().build();
        app.setAppKey("123");
        app.setAppName("test");
        app.setAppSecret("new_secret");
        app.setCallbackUrl("http://www.baidu.com");
        app.setOpen(true);
        app.setDescription("test");
        app.setCreateTime(new Date().toString());
        appMapper.insert(app);
        logger.info("Insert app {}", app.toString());
    }

    @Test
    public void getAppByAppKey() {

        logger.info("Get app");
        final String appkey = "123";
        AppEntity app = appMapper.selectByAppKey(appkey);
        if (app != null)
            logger.info("Get app {}", app.toString());
    }


    @Test
    public void updateAppByAppKey() {
        logger.info("Update app");
        final String appKey = "123";
        final String updateCallbackUrl = "http://www.sohu.com";
        AppEntity app = appMapper.selectByAppKey(appKey);
        app.setCallbackUrl(updateCallbackUrl);
        appMapper.updateByAppKey(app);
        AppEntity updatedApp = appMapper.selectByAppKey(appKey);
        Assert.isTrue(updatedApp != null && updateCallbackUrl.equals(updatedApp.getCallbackUrl()),
                "Update success");
    }

    @Test
    public void deleteByAppKey() {
        logger.info("Delete app");
        final String appKey = "123";
        appMapper.deleteByAppKey(appKey);
        logger.info("Delete App success!");
        int total = appMapper.total();
        Assert.isTrue(total == 0, "Delete uccess");
    }

}
