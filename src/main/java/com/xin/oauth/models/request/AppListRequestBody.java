package com.xin.oauth.models.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-03-01 12:56
 * @class App List Request Body
 */

@Data
@Builder
public class AppListRequestBody {

    private Long userId;
}
