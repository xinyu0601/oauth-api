package com.xin.oauth.models.entity;

import com.xin.oauth.models.bo.AppBO;
import com.xin.oauth.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xinyu.huang02
 * @date 2020-02-24 22:04
 * @class App Info Model
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppEntity {

    private Long id;

    private String appName;

    private String appSecret;

    private String appKey;

    private String description;

    private String callbackUrl;

    private String createTime;

    private String updateTime;

    private Long userId;

    private boolean isOpen;

    public void updateFromBO(AppBO appBO) {
        if (StringUtils.isNotBlank(appBO.getAppName()))
            this.appName = appBO.getAppName();
        if (StringUtils.isNotBlank(appBO.getAppSecret()))
            this.appSecret = appBO.getAppSecret();
        if (StringUtils.isNotBlank(appBO.getAppKey()))
            this.appKey = appBO.getAppKey();
        if (StringUtils.isNotBlank(appBO.getDescription()))
            this.description = appBO.getDescription();
        if (StringUtils.isNotBlank(appBO.getCallbackUrl()))
            this.callbackUrl = appBO.getCallbackUrl();
        this.isOpen = appBO.isOpen();
        this.updateTime = DateUtils.currentTimeStr();
    }

    public static AppEntity fromBO(AppBO appBO) {
        return AppEntity.builder()
                .appName(appBO.getAppName())
                .appSecret(appBO.getAppSecret())
                .appKey(appBO.getAppKey())
                .description(appBO.getDescription())
                .callbackUrl(appBO.getCallbackUrl())
                .isOpen(appBO.isOpen())
                .createTime(DateUtils.currentTimeStr())
                .updateTime(DateUtils.currentTimeStr())
                .userId(appBO.getUserId())
                .build();
    }
}
