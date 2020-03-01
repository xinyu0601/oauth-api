package com.xin.oauth.models.bo;

import com.xin.oauth.models.entity.AppEntity;
import com.xin.oauth.models.request.AppInfoRequestBody;
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

    private String userId;

    private boolean isOpen;

    public static AppBO fromBody(AppInfoRequestBody body) {
        return AppBO.builder()
                .appName(body.getAppName())
                .callbackUrl(body.getCallbackUrl())
                .description(body.getDescription())
                .userId(body.getUserId())
                .isOpen(body.isOpen()).build();
    }

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
