package com.xin.oauth.service.impl;

import com.xin.oauth.service.SMSService;

/**
 * @author xinyu.huang02
 * @date 2020-02-28 11:30
 * @class 短信相关操作业务逻辑实现类
 */
public class SMSServiceImpl implements SMSService {

    @Override
    public boolean verifySMSCode(String code) {
        return false;
    }

}
