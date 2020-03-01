package com.xin.oauth.models.entity;

import com.xin.oauth.models.bo.AppBO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String userId;

    private boolean isOpen;

    public void updateFromBO(AppBO appBO) {
        this.appName = appBO.getAppName();
        this.appSecret = appBO.getAppSecret();
        this.appKey = appBO.getAppKey();
        this.description = appBO.getDescription();
        this.callbackUrl = appBO.getDescription();
        this.isOpen = appBO.isOpen();
    }

    public static AppEntity fromBO(AppBO appBO) {
        return AppEntity.builder()
                .appName(appBO.getAppName())
                .appSecret(appBO.getAppSecret())
                .appKey(appBO.getAppKey())
                .description(appBO.getDescription())
                .callbackUrl(appBO.getDescription())
                .isOpen(appBO.isOpen())
                .createTime(appBO.getCreateTime())
                .build();
    }
}
