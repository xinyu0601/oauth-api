package com.xin.oauth.controller;

import com.xin.oauth.models.ao.ResultAO;
import com.xin.oauth.models.request.UserInfoRequestBody;
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
public class UserController {

    /**
     * 根据accessToken获取用户信息
     *
     * @param userInfoRequestBody
     * @return
     */
    @PostMapping("/user_info")
    public ResultAO<Object> getUserInfo(@RequestBody UserInfoRequestBody userInfoRequestBody) {
        return new ResultAO<Object>();
    }


}
