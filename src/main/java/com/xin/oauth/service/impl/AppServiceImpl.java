package com.xin.oauth.service.impl;

import com.xin.oauth.mapper.AppMapper;
import com.xin.oauth.models.bo.AppBO;
import com.xin.oauth.models.entity.AppEntity;
import com.xin.oauth.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 14:50
 * @class App业务实现类
 */
@Service
public class AppServiceImpl implements AppService {

    private static final Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    private AppMapper appMapper;


    public void saveApp(AppBO appBO) {
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
        logger.info("Find app when app = {}, appSecret = {}", appKey, appSecret);
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
        logger.info("Find app when app = {}, appSecret = {}", appKey, appSecret);
        AppEntity appEntity = appMapper.selectByAppKeyAndAppSecret(appKey, appSecret);
        if (appEntity == null) {
            return null;
        }
        logger.info("Find app {}", appEntity.toString());
        return AppBO.fromEntity(appEntity);
    }
}
