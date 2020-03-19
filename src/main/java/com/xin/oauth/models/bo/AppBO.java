package com.xin.oauth.models.bo;

import com.xin.oauth.models.entity.AppEntity;
import com.xin.oauth.models.request.AppInfoRequestBody;
import com.xin.oauth.models.request.UpdateAppRequestBody;
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

    private Long appId;

    private String appName;

    private String appSecret;

    private String appKey;

    private String description;

    private String callbackUrl;

    private String createTime;

    private String updateTime;

    private Long userId;

    private boolean isOpen;

    public static AppBO fromUpdateBody(UpdateAppRequestBody body) {
        return AppBO.builder()
                .appName(body.getAppName())
                .callbackUrl(body.getCallbackUrl())
                .description(body.getDescription())
                .isOpen(body.isOpen()).build();
    }

    public static AppBO fromRegisterBody(AppInfoRequestBody body) {
        return AppBO.builder()
                .appName(body.getAppName())
                .callbackUrl(body.getCallbackUrl())
                .description(body.getDescription())
                .userId(body.getUserId())
                .isOpen(body.isOpen()).build();
    }

    public static AppBO fromEntity(AppEntity appEntity) {
        return AppBO.builder()
                .appId(appEntity.getId())
                .description(appEntity.getDescription())
                .appKey(appEntity.getAppKey())
                .userId(appEntity.getUserId())
                .appName(appEntity.getAppName())
                .appKey(appEntity.getAppKey())
                .appSecret(appEntity.getAppSecret())
                .callbackUrl(appEntity.getCallbackUrl())
                .isOpen(appEntity.isOpen())
                .updateTime(appEntity.getUpdateTime())
                .createTime(appEntity.getCreateTime()).build();
    }

}
