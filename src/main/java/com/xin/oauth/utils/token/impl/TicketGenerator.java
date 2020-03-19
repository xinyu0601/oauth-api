package com.xin.oauth.utils.token.impl;

import com.xin.oauth.exceptions.TokenException;
import com.xin.oauth.utils.DateUtils;
import com.xin.oauth.utils.EncryptUtils;
import com.xin.oauth.utils.token.AbstractKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


/**
 * @author xinyu.huang02
 * @date 2020-02-27 14:48
 * @class Ticket 生成器
 */
@Slf4j
public class TicketGenerator extends AbstractKeyGenerator {

    public String generate(String appKey, String scope) {
        if (StringUtils.isBlank(appKey) || StringUtils.isBlank(scope))
            throw new TokenException("App key or scope is blank");
        String temp = appKey + scope + DateUtils.currentTimeInMillis();
        String ticket = EncryptUtils.sha1Hex(temp);
        log.info("Generate new access token :" + ticket);
        return ticket;
    }

}
