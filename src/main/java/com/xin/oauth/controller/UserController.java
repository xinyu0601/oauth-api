package com.xin.oauth.controller;

import com.xin.oauth.annotation.NeedToken;
import com.xin.oauth.models.ao.ResultAO;
import com.xin.oauth.models.request.UserInfoRequestBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 21:48
 * @class 用户信息控制层
 */

@RestController
@RequestMapping("/user")
@Api(value = "User controller")
@Slf4j
public class UserController {

    /**
     * 根据accessToken获取用户信息, 需要AccessToken验证
     *
     * @param userInfoRequestBody
     * @return
     */
    @PostMapping("/user_info")
    @ApiOperation(value = "根据accessToken获取用户信息")
    @NeedToken
    public ResultAO<Object> getUserInfo(@RequestBody UserInfoRequestBody userInfoRequestBody) {
        return new ResultAO<Object>();
    }


}
