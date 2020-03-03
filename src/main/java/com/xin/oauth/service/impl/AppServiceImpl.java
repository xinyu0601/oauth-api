package com.xin.oauth.service.impl;

import com.xin.oauth.exceptions.AppException;
import com.xin.oauth.mapper.AppMapper;
import com.xin.oauth.models.bo.AppBO;
import com.xin.oauth.models.entity.AppEntity;
import com.xin.oauth.service.AppService;
import com.xin.oauth.utils.token.AppKeyGenerator;
import com.xin.oauth.utils.token.AppSecretGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 14:50
 * @class App业务实现类
 */
@Service
public class AppServiceImpl implements AppService {

    private static final Logger log = LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private AppKeyGenerator appKeyGenerator;

    @Autowired
    private AppSecretGenerator appSecretGenerator;


    /**
     * 注册新的APP
     *
     * @param appBO
     */
    public AppBO registerApp(AppBO appBO) {
        AppEntity newApp = AppEntity.fromBO(appBO);
        String appKey = appKeyGenerator.generate();
        String appSecret = appSecretGenerator.generate();
        newApp.setAppKey(appKey);
        newApp.setAppSecret(appSecret);
        log.info(String.format("Register new app [%s]", newApp.toString()));
        appMapper.insert(newApp);
        newApp = appMapper.selectByAppKeyAndAppSecret(appKey, appSecret);
        if (newApp == null)
            throw new AppException(String.format("Insert app %s failed", appBO.toString()));
        log.info(String.format("New app entity = [%s]", newApp.toString()));
        return AppBO.fromEntity(newApp);
    }


    /**
     * 根据AppId删除App
     *
     * @param appId
     */
    @Override
    public void removeApp(Long appId) {
        log.info(String.format("Remove app id = %s", appId));
        appMapper.delete(appId);
    }

    /**
     * 根据App Name删除App
     *
     * @param appName
     */
    @Override
    public void removeByAppName(String appName) {
        log.info(String.format("Remove app name = %s", appName));
        appMapper.deleteByAppName(appName);
    }


    /**
     * 更新APP
     *
     * @param appId
     * @param appBO
     */
    @Override
    public AppBO updateApp(Long appId, AppBO appBO) {
        AppEntity appEntity = appMapper.selectByAppId(appId);
        appEntity.updateFromBO(appBO);
        appMapper.update(appEntity);
        AppEntity updatedAppEntity = appMapper.selectByAppId(appId);
        return AppBO.fromEntity(updatedAppEntity);
    }


    /**
     * 通过UID，返回APP列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<AppBO> all(Long userId) {
        List<AppEntity> appEntities = appMapper.selectByUserId(userId);
        List<AppBO> appBOS = new ArrayList<>(appEntities.size());
        for (AppEntity appEntity : appEntities) {
            appBOS.add(AppBO.fromEntity(appEntity));
        }
        return appBOS;
    }


    /**
     * 验证AppKey和AppSecret对应的App是否存在
     *
     * @param appKey
     * @param appSecret
     * @return
     */
    @Override
    public boolean verifyByAppKeyAndAppSecret(String appKey, String appSecret) {
        log.info("Find app when app = {}, appSecret = {}", appKey, appSecret);
        AppEntity appEntity = appMapper.selectByAppKeyAndAppSecret(appKey, appSecret);
        return appEntity != null;
    }


    /**
     * 通过AppKey和AppSecret查找App
     *
     * @param appKey
     * @param appSecret
     * @return
     */
    @Override
    public AppBO findByAppKeyAndAppSecret(String appKey, String appSecret) {
        log.info("Find app when app = {}, appSecret = {}", appKey, appSecret);
        AppEntity appEntity = appMapper.selectByAppKeyAndAppSecret(appKey, appSecret);
        if (appEntity == null) {
            return null;
        }
        log.info("Find app {}", appEntity.toString());
        return AppBO.fromEntity(appEntity);
    }
}
