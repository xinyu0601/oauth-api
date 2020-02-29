package com.xin.oauth.service;

import com.xin.oauth.models.bo.AccessTokenBO;
import com.xin.oauth.models.bo.AuthRefreshTokenBO;
import com.xin.oauth.models.bo.TicketBO;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 14:52
 * @class Oauth Service 接口
 */
public interface OauthService {

    boolean verifyTicket(String ticketToken);

    boolean verifyAccessToken(String accessToken);

    AccessTokenBO generateAccessToken(String appKey, String appSecret);

    TicketBO generateTicket();

    AuthRefreshTokenBO findRefreshToken(String refreshToken);

    AccessTokenBO findTokenByTokenId(String tokenId);

    void expireTicket(String ticket);

    void expireToken(String token);

}
