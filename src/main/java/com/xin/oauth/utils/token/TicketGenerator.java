package com.xin.oauth.utils.token;

import com.xin.oauth.utils.DateUtils;
import com.xin.oauth.utils.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author xinyu.huang02
 * @date 2020-02-27 14:48
 * @class Ticket 生成器
 */

@Component
public class TicketGenerator {

    private static final Logger log = LoggerFactory.getLogger(AccessTokenGenerator.class);

    public String generate(String appId, String scope) {
        String temp = appId + scope + DateUtils.currentTimeInMillis();
        String ticket = EncryptUtils.sha1Hex(temp);
        log.info("Generate new access token :" + ticket);
        return ticket;
    }

}
