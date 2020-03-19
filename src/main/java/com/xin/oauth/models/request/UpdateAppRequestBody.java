package com.xin.oauth.models.request;

import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-03-04 16:43
 * @class Update app request body
 */
@Data
public class UpdateAppRequestBody {

    private String iconUrl;

    private String appName;

    private String description;

    private String callbackUrl;

    private boolean isOpen;

}
