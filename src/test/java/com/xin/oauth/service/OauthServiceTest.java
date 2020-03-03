package com.xin.oauth.service;

import com.xin.oauth.OauthApplicationTests;
import com.xin.oauth.models.bo.AccessTokenBO;
import com.xin.oauth.models.bo.TicketBO;
import com.xin.oauth.utils.token.AppKeyGenerator;
import com.xin.oauth.utils.token.AppSecretGenerator;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 14:39
 * @class Oauth service test case
 */

@FixMethodOrder(MethodSorters.JVM)
public class OauthServiceTest extends OauthApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(OauthServiceTest.class);

    @Autowired
    private OauthService oauthService;

    @Autowired
    private AppKeyGenerator appKeyGenerator;

    @Autowired
    private AppSecretGenerator appSecretGenerator;

    private String appKey;

    private String appSecret;

    @Before
    public void setUp() {
        this.appKey = appKeyGenerator.generate();
        this.appSecret = appSecretGenerator.generate();
    }

    @Test
    public void testGenerateAccessToken() {
        AccessTokenBO accessTokenBO = oauthService.generateAccessToken(appKey, appSecret);
        assertNotNull("Access token generate failed", accessTokenBO);
        oauthService.removeAccessTokenById(accessTokenBO.getTokenId());
    }


    @Test
    public void testGenerateTicket() {
        TicketBO ticketBO = oauthService.generateTicket(appKey, appSecret);
        assertNotNull("Generate ticket failed", ticketBO);
    }

    @Test
    public void testVerifyTicket() {
        TicketBO ticketBO = oauthService.generateTicket(appKey, appSecret);
        boolean isTrue = oauthService.verifyTicket(ticketBO);
        assertTrue("Verify ticket failed", isTrue);
    }


    @Test
    public void testVerifyAccessToken() {
        AccessTokenBO accessTokenBO = oauthService.generateAccessToken(appKey, appSecret);
        assertNotNull("Generate access token failed", accessTokenBO);
        boolean isTrue = oauthService.verifyAccessToken(accessTokenBO);
        assertTrue("Verify token failed", isTrue);
        oauthService.removeAccessTokenById(accessTokenBO.getTokenId());
    }


    @Test
    public void testFindRefreshToken() {
        AccessTokenBO accessTokenBO = oauthService.generateAccessToken(appKey, appSecret);
        assertNotNull("Generate access token failed", accessTokenBO);
        assertNotNull("Generate access token failed", accessTokenBO.getRefreshToken());
        assertNotNull("token id is null", accessTokenBO.getTokenId());
        oauthService.removeAccessTokenById(accessTokenBO.getTokenId());
    }

    @Test
    public void testFindTokenByTokenId() {
        AccessTokenBO accessToken = oauthService.generateAccessToken(appKey, appSecret);
        AccessTokenBO findAccessToken = oauthService.findTokenByTokenId(accessToken.getTokenId());
        assertNotNull("find access token failed", findAccessToken);
        assertNotNull("token id is null", findAccessToken.getTokenId());
        oauthService.removeAccessTokenById(accessToken.getTokenId());
    }

    @Test
    public void testExpiresTicket() {
        TicketBO ticketBO = oauthService.generateTicket(appKey, appSecret);
        assertNotNull("Generate ticket failed", ticketBO);
        oauthService.expiresTicket(ticketBO.getTicket());
        boolean isExist = oauthService.verifyTicket(ticketBO);
        assertFalse("Expires ticket failed", isExist);

    }

    @Test
    public void testExpiresToken() {
        AccessTokenBO accessTokenBO = oauthService.generateAccessToken(appKey, appSecret);
        assertNotNull("Generate access token failed", accessTokenBO);
        oauthService.expiresToken(accessTokenBO.getAccessToken());
        boolean isExist = oauthService.verifyAccessToken(accessTokenBO);
        assertFalse("Expires ticket failed", isExist);
        oauthService.removeAccessTokenById(accessTokenBO.getTokenId());
    }

    @Test
    public void end() {

    }
}
