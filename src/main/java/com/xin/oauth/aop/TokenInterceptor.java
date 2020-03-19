package com.xin.oauth.aop;

import com.alibaba.fastjson.JSON;
import com.xin.oauth.annotation.NeedToken;
import com.xin.oauth.enums.ResultCodeEnum;
import com.xin.oauth.service.OauthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xinyu.huang02
 * @date 2020-03-04 18:11
 * @class Token拦截器
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private OauthService oauthService;

    /**
     * 检查Access Token是否已经失效
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            if (((HandlerMethod) handler).hasMethodAnnotation(NeedToken.class)) {
                String accessToken = request.getHeader("access_token");
                if (StringUtils.isBlank(accessToken) || !oauthService.verifyAccessToken(accessToken)) {
                    return this.errorResponse(response, ResultCodeEnum.NO_AUTH);
                }
            }
        }
        return true;
    }


    /**
     * 组装错误请求的返回
     */
    private boolean errorResponse(HttpServletResponse response, ResultCodeEnum resultCodeEnum) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        Map<String, String> result = new HashMap<>(2);
        result.put("code", String.valueOf(resultCodeEnum.getCode()));
        result.put("msg", resultCodeEnum.getDesc());
        result.put("data", null);
        response.getWriter().write(JSON.toJSONString(result));
        return false;
    }

}
