package com.xin.oauth.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 12:10
 * @class Register AppEntity Request Body
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInfoRequestBody {

    private String iconUrl;

    private String appNameEn;

    private String appName;

    private String description;

    private String callbackUrl;

    private String userId;

    private boolean isOpen;
}
