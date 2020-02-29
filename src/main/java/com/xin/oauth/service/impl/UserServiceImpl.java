package com.xin.oauth.service.impl;

import com.xin.oauth.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 16:37
 * @class 用户业务实现类
 */

@Service
public class UserServiceImpl implements UserService {

    /**
     * 通过用户名和密码验证用户身份
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean verifyUser(String userName, String password) {
        return false;
    }

}
