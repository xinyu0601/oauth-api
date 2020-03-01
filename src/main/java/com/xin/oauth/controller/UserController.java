package com.xin.oauth.controller;

import com.xin.oauth.models.ao.ResultAO;
import com.xin.oauth.models.request.UserInfoRequestBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 21:48
 * @class 用户信息控制层
 */

@RestController
@RequestMapping("/user")
@Api(value = "User controller")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 根据accessToken获取用户信息
     *
     * @param userInfoRequestBody
     * @return
     */
    @PostMapping("/user_info")
    @ApiOperation(value = "根据accessToken获取用户信息")
    public ResultAO<Object> getUserInfo(@RequestBody UserInfoRequestBody userInfoRequestBody) {
        return new ResultAO<Object>();
    }


}
