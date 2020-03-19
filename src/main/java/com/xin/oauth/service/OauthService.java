package com.xin.oauth.service;

import com.xin.oauth.models.bo.AccessTokenBO;
import com.xin.oauth.models.bo.TicketBO;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 14:52
 * @class Oauth Service 接口
 */
public interface OauthService {

    boolean verifyTicket(TicketBO ticketBO);

    boolean verifyTicket(String ticket);

    boolean verifyAccessToken(AccessTokenBO accessToken);

    boolean verifyAccessToken(String accessToken);

    boolean verifyRefreshToken(String refreshToken);

    void removeAccessTokenById(Long tokenId);

    AccessTokenBO generateAccessToken(String appKey, String appSecret);

    TicketBO generateTicket(String appKey, String appSecret);

    AccessTokenBO findAccessToken(String accessToken);

    AccessTokenBO findTokenByTokenId(Long tokenId);

    void expiresTicket(String ticket);

    void expiresToken(String token);

}
