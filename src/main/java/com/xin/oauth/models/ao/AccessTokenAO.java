package com.xin.oauth.models.ao;

import com.xin.oauth.models.bo.AccessTokenBO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xinyu.huang02
 * @date 2020-02-28 00:16
 * @class Access Token 返回类
 */
@Data
@Builder
@NoArgsConstructor
public class AccessTokenAO {

    private String accessToken;

    private Long expireAt;

    public static AccessTokenAO fromBO(AccessTokenBO accessTokenBO) {
        if (accessTokenBO == null)
            return new AccessTokenAO();
        return AccessTokenAO.builder()
                .accessToken(accessTokenBO.getAccessToken())
                .expireAt(accessTokenBO.getExpireAt()).build();
    }
}
