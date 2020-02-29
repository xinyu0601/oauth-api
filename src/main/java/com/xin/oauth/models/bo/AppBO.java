package com.xin.oauth.models.bo;

import com.xin.oauth.models.entity.AppEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 11:39
 * @class App BO
 */

@Data
@Builder
public class AppBO {

    private String appName;

    private String appSecret;

    private String appKey;

    private String description;

    private String callbackUrl;

    private String createTime;

    private boolean isOpen;

    public static AppBO fromEntity(AppEntity appEntity) {
        return AppBO.builder()
                .appKey(appEntity.getAppKey())
                .appName(appEntity.getAppName())
                .appKey(appEntity.getAppKey())
                .appSecret(appEntity.getAppSecret())
                .callbackUrl(appEntity.getCallbackUrl())
                .isOpen(appEntity.isOpen())
                .createTime(appEntity.getCreateTime()).build();
    }

}
