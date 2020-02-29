package com.xin.oauth.service.impl;

import com.xin.oauth.models.bo.AccessTokenBO;
import com.xin.oauth.models.bo.AuthRefreshTokenBO;
import com.xin.oauth.models.bo.TicketBO;
import com.xin.oauth.service.OauthService;
import com.xin.oauth.utils.RedisOperator;
import com.xin.oauth.utils.token.AccessTokenGenerator;
import com.xin.oauth.utils.token.TicketGenerator;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 14:53
 * @class Oauth 业务实现类
 */
@Service
public class OauthServiceImpl implements OauthService {

    private static final Log log = LogFactory.getLog(OauthServiceImpl.class);

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private AccessTokenGenerator accessTokenGenerator;

    @Autowired
    private TicketGenerator ticketGenerator;

    /**
     * 验证ticket token是否有效
     *
     * @param ticketToken
     * @return
     */
    @Override
    public boolean verifyTicket(String ticketToken) {
        return false;
    }

    /**
     * 验证access token 是否有效
     *
     * @param accessToken
     * @return
     */
    @Override
    public boolean verifyAccessToken(String accessToken) {
        return false;
    }

    /**
     * 生成Access token
     *
     * @return
     */
    @Override
    public AccessTokenBO generateAccessToken(String appKey, String appSecret) {
        return null;
    }

    /**
     * 生成ticket token用于app换取access token
     *
     * @return
     */
    @Override
    public TicketBO generateTicket() {
        return null;
    }

    /**
     * 根据Refresh Token值获取Refresh Token
     *
     * @param refreshToken
     * @return
     */
    @Override
    public AuthRefreshTokenBO findRefreshToken(String refreshToken) {
        return null;
    }

    /**
     * 根据TokenId 查找Access Token
     *
     * @param tokenId
     * @return
     */
    @Override
    public AccessTokenBO findTokenByTokenId(String tokenId) {
        return null;
    }

    @Override
    public void expireTicket(String ticket) {

    }

    @Override
    public void expireToken(String token) {

    }
}
