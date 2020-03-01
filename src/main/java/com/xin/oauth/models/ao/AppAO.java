package com.xin.oauth.models.ao;


import com.xin.oauth.models.bo.AppBO;
import lombok.Builder;
import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-02-29 21:56
 * @class APP AO
 */
@Data
@Builder
public class AppAO {

    private String appName;

    private String appSecret;

    private String appKey;

    private String description;

    private String callbackUrl;

    private String createTime;

    private String userId;

    private boolean isOpen;

    public static AppAO fromBO(AppBO appBO) {
        return AppAO.builder()
                .appKey(appBO.getAppKey())
                .appName(appBO.getAppName())
                .appKey(appBO.getAppKey())
                .appSecret(appBO.getAppSecret())
                .callbackUrl(appBO.getCallbackUrl())
                .isOpen(appBO.isOpen())
                .createTime(appBO.getCreateTime()).build();
    }

}
