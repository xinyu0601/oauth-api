package com.xin.oauth.controller;

import com.xin.oauth.enums.ResultCodeEnum;
import com.xin.oauth.exceptions.AppException;
import com.xin.oauth.exceptions.SMSException;
import com.xin.oauth.exceptions.TokenException;
import com.xin.oauth.exceptions.UserLoginException;
import com.xin.oauth.models.ao.AccessTokenAO;
import com.xin.oauth.models.ao.ResultAO;
import com.xin.oauth.models.bo.AccessTokenBO;
import com.xin.oauth.models.bo.AppBO;
import com.xin.oauth.models.bo.TicketBO;
import com.xin.oauth.models.request.*;
import com.xin.oauth.service.AppService;
import com.xin.oauth.service.OauthService;
import com.xin.oauth.service.SMSService;
import com.xin.oauth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author xinyu.huang02
 * @date 2020-02-24 20:02
 * @class Oauth2 Controller
 */
@RestController
@RequestMapping("/oauth2")
@Api(value = "Oauth2 Controller")
public class OauthController {

    private static final Logger log = LoggerFactory.getLogger(OauthController.class);

    @Autowired
    private OauthService oauthService;

    @Autowired
    private AppService appService;

    @Autowired
    private UserService userService;

    @Autowired
    private SMSService smsService;

    /**
     * 跳转到指定页面，填写用户登录信息
     *
     * @param authorizeRequestBody
     * @return
     */
    @PostMapping("/authorize_page")
    @ApiOperation(value = "跳转到用户授权页面接口")
    public ModelAndView authorize(@RequestBody AuthorizePageRequestBody authorizeRequestBody) {
        String appKey = authorizeRequestBody.getAppKey();
        String appSecret = authorizeRequestBody.getAppSecret();
        AppBO appBO = appService.findByAppKeyAndAppSecret(appKey, appSecret);
        if (appBO == null)
            throw new AppException(String.format("App[key = %s, secret = %s] not found", appKey, appSecret));
        // 跳转到授权页面
        String oauthHomePage = "";
        return new ModelAndView(new RedirectView(oauthHomePage, true), null);
    }


    /**
     * 输入用户名和密码登录，验证通过生成ticket接口
     *
     * @param authorizeRequestBody
     * @return
     */
    @PostMapping("/authorize")
    @ApiOperation(value = "通过用户名，密码以及短信验证码生成ticket, 并请求回调接口，传递ticket")
    public ModelAndView login(@RequestBody AuthorizeRequestBody authorizeRequestBody) {
        String userName = authorizeRequestBody.getUserName();
        String password = authorizeRequestBody.getPassword();
        String smsCode = authorizeRequestBody.getSmsCode();
        String status = authorizeRequestBody.getStatus();
        String appKey = authorizeRequestBody.getAppKey();
        String appSecret = authorizeRequestBody.getAppSecret();

        if (!userService.verifyUser(userName, password))
            throw new UserLoginException(String.format("User[name = %s] is not found", userName));
        if (!smsService.verifySMSCode(smsCode))
            throw new SMSException(String.format("sms code [%s] is expired", smsCode));

        AppBO appBO = appService.findByAppKeyAndAppSecret(appKey, appSecret);
        TicketBO ticketBO = oauthService.generateTicket(appKey, appSecret);

        String params = "?ticket=" + ticketBO.getTicket();
        if (StringUtils.isNoneBlank(status)) {
            params = params + "&status=" + status;
        }
        final String redirectUrl = String.format("redirect:%s%s", appBO.getCallbackUrl(), params);
        return new ModelAndView(redirectUrl);
    }


    /**
     * App通过获取到的ticket生成Access Token接口
     *
     * @param accessTokenRequestBody
     * @return
     */
    @PostMapping("/access_token")
    @ApiOperation(value = "App通过获取到的ticket生成Access Token接口")
    public ResultAO<AccessTokenAO> accessToken(@RequestBody AccessTokenRequestBody accessTokenRequestBody) {
        String ticket = accessTokenRequestBody.getTicket();
        String appKey = accessTokenRequestBody.getAppKey();
        String appSecret = accessTokenRequestBody.getAppSecret();
        String scope = accessTokenRequestBody.getScope();

        AppBO appBO = appService.findByAppKeyAndAppSecret(appKey, appSecret);
        if (appBO == null)
            throw new AppException(String.format("App [key = %s, secret = %s] not found", appKey, appSecret));
        AccessTokenBO accessTokenBO = null;
        TicketBO ticketBO = TicketBO.builder()
                .ticket(ticket)
                .appKey(appKey)
                .appSecret(appSecret)
                .scope(scope).build();
        if (oauthService.verifyTicket(ticketBO)) {
            accessTokenBO = oauthService.generateAccessToken(appKey, appSecret);
            if (accessTokenBO == null)
                throw new TokenException("Generate New Access token failed");

        }
        return new ResultAO<>(ResultCodeEnum.COMMON_SUCCESS, AccessTokenAO.fromBO(accessTokenBO));
    }


    /**
     * App主动更新Access Token接口
     *
     * @return
     */
    @PostMapping("/refresh_token")
    @ApiOperation(value = "App主动更新Access Token接口")
    public ResultAO<AccessTokenAO> refreshToken(@RequestBody RefreshTokenRequestBody refreshTokenRequestBody) {
        String refreshToken = refreshTokenRequestBody.getRefreshToken();
        Long tokenId = refreshTokenRequestBody.getTokenId();
        AccessTokenBO authRefreshTokenBO = oauthService.findRefreshToken(refreshToken);
        if (authRefreshTokenBO == null)
            throw new TokenException(String.format("Refresh token [%s] not found", refreshToken));
        AccessTokenBO accessTokenBO = oauthService.findTokenByTokenId(tokenId);
        if (accessTokenBO == null)
            throw new TokenException(String.format("Access token [%s] not found", tokenId));
        AccessTokenBO newAccessTokenBO = oauthService.generateAccessToken(accessTokenBO.getAppKey(), accessTokenBO.getAppSecret());
        if (newAccessTokenBO == null)
            throw new TokenException("Generate New Access token failed");
        return new ResultAO<AccessTokenAO>(ResultCodeEnum.COMMON_SUCCESS, AccessTokenAO.fromBO(newAccessTokenBO));
    }


    /**
     * 登出操作
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "登出操作接口")
    public ResultAO<String> logout(@RequestBody LogoutRequestBody logoutRequestBody) {
        oauthService.expiresTicket(logoutRequestBody.getTicket());
        oauthService.expiresToken(logoutRequestBody.getAccessToken());
        return new ResultAO<String>(ResultCodeEnum.COMMON_SUCCESS, "logout success");
    }


}
