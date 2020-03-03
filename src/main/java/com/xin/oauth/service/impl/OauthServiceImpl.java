package com.xin.oauth.service.impl;

import com.xin.oauth.constants.Constants;
import com.xin.oauth.exceptions.TokenException;
import com.xin.oauth.mapper.TokenMapper;
import com.xin.oauth.models.bo.AccessTokenBO;
import com.xin.oauth.models.bo.TicketBO;
import com.xin.oauth.models.entity.TokenEntity;
import com.xin.oauth.service.OauthService;
import com.xin.oauth.utils.DateUtils;
import com.xin.oauth.utils.RedisUtils;
import com.xin.oauth.utils.token.AccessTokenGenerator;
import com.xin.oauth.utils.token.RefreshTokenGenerator;
import com.xin.oauth.utils.token.TicketGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 14:53
 * @class Oauth 业务实现类
 */
@Service
public class OauthServiceImpl implements OauthService {

    private static final Logger log = LoggerFactory.getLogger(OauthServiceImpl.class);

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AccessTokenGenerator accessTokenGenerator;

    @Autowired
    private TicketGenerator ticketGenerator;

    @Autowired
    private RefreshTokenGenerator refreshTokenGenerator;

    @Autowired
    private TokenMapper tokenMapper;

    /**
     * 验证ticket token是否有效
     *
     * @param ticketBO
     * @return
     */
    @Override
    public boolean verifyTicket(TicketBO ticketBO) {
        String scopeValue = redisUtils.get(ticketBO.getTicket() + ":scope");
        return StringUtils.isNotBlank(scopeValue);
    }


    /**
     * 验证access token 是否有效
     *
     * @param accessToken
     * @return
     */
    @Override
    public boolean verifyAccessToken(AccessTokenBO accessToken) {
        String accessTokenValue = redisUtils.get(accessToken.getAccessToken() + ":scope");
        return StringUtils.isNotBlank(accessTokenValue);
    }


    /**
     * 根据TokenId删除token
     *
     * @param tokenId
     */
    @Override
    public void removeAccessTokenById(Long tokenId) {
        log.info(String.format("Delete access token [%d]", tokenId));
        tokenMapper.deleteByTokenId(tokenId);
    }


    /**
     * 生成Access token
     *
     * @return
     */
    @Override
    public AccessTokenBO generateAccessToken(String appKey, String appSecret) {
        String accessToken = accessTokenGenerator.generate(appKey, appSecret);
        String refreshToken = refreshTokenGenerator.generate(appKey, appSecret);
        log.info(String.format("Generate accessToken = %s, refrestToken = %s", accessToken, refreshToken));
        AccessTokenBO accessTokenBO = AccessTokenBO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .appKey(appKey)
                .appSecret(appSecret)
                .expiresIn(DateUtils.currentTimeAfterDay())
                .scope(Constants.DEFAULT_SCOPE).build();
        TokenEntity newTokenEntity = TokenEntity.fromBO(accessTokenBO);
        tokenMapper.insert(newTokenEntity);
        newTokenEntity = tokenMapper.selectByAccessToken(accessToken);
        if (newTokenEntity == null)
            throw new TokenException("Insert token failed");
        log.info("Insert access token " + newTokenEntity.toString());
        String tokenKey = accessToken + ":" + Constants.DEFAULT_SCOPE;
        redisUtils.set(tokenKey, accessToken, Constants.TOKEN_EXPIRES_TIME);
        return AccessTokenBO.fromEntity(newTokenEntity);
    }


    /**
     * 生成ticket token用于app换取access token
     *
     * @return
     */
    @Override
    public TicketBO generateTicket(String appKey, String appSecret) {
        String ticket = ticketGenerator.generate(appKey, appSecret);
        log.info(String.format("Generate ticket = %s", ticket));
        TicketBO newTicket = TicketBO.builder()
                .ticket(ticket)
                .appKey(appKey)
                .appSecret(appSecret)
                .scope(Constants.DEFAULT_SCOPE).build();
        String ticketKey = ticket + ":" + Constants.DEFAULT_SCOPE;
        redisUtils.set(ticketKey, ticket, Constants.TICKET_EXPIRES_TIME);
        return newTicket;
    }


    /**
     * 根据Refresh Token值获取Refresh Token
     *
     * @param refreshToken
     * @return
     */
    @Override
    public AccessTokenBO findRefreshToken(String refreshToken) {
        TokenEntity tokenEntity = tokenMapper.selectByRefreshToken(refreshToken);
        log.info(String.format("Find token %s", tokenEntity.toString()));
        return AccessTokenBO.fromEntity(tokenEntity);
    }


    /**
     * 根据TokenId 查找Access Token
     *
     * @param tokenId
     * @return
     */
    @Override
    public AccessTokenBO findTokenByTokenId(Long tokenId) {
        TokenEntity tokenEntity = tokenMapper.selectByTokenId(tokenId);
        log.info(String.format("Find token %s", tokenEntity.toString()));
        return AccessTokenBO.fromEntity(tokenEntity);
    }


    /**
     * 设置ticket过期
     *
     * @param ticket
     */
    @Override
    public void expiresTicket(String ticket) {
        String ticketKey = ticket + ":" + Constants.DEFAULT_SCOPE;
        redisUtils.del(ticketKey);
    }


    /**
     * 设置Tocken过期
     *
     * @param token
     */
    @Override
    public void expiresToken(String token) {
        String tokenKey = token + ":" + Constants.DEFAULT_SCOPE;
        redisUtils.del(tokenKey);
    }
}
