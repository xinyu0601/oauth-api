package com.xin.oauth.utils;

import com.xin.oauth.OauthApplicationTests;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 14:23
 * @class Redis基本操作测试类
 */
public class RedisOperatorTest extends OauthApplicationTests {

    private static final Log log = LogFactory.getLog(RedisOperatorTest.class);

    @Autowired
    private RedisOperator redisOperator;

    @Test
    public void testSetKey() {
        redisOperator.set("1", "2", 1000);
    }

    @Test
    public void testTtl() {
        long leftTime = redisOperator.ttl("1");
        log.info("left time = " + leftTime);
    }
}
