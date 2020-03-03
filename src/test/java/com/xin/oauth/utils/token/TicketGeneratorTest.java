package com.xin.oauth.utils.token;

import com.xin.oauth.exceptions.TokenException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xinyu.huang02
 * @date 2020-03-02 10:45
 * @class Ticket generator test case
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketGeneratorTest {

    private static final Logger log = LoggerFactory.getLogger(TicketGeneratorTest.class);

    @Autowired
    private TicketGenerator ticketGenerator;

    @Autowired
    private AppKeyGenerator appKeyGenerator;

    @Test
    public void generate() {
        String appKey = appKeyGenerator.generate();
        String ticket = ticketGenerator.generate(appKey, "scope");
        log.info("ticket = " + ticket);
    }


    @Test
    public void testGenerateIfAppKeyIsNull() {
        try {
            String ticket = ticketGenerator.generate(null, "scope");
            log.info("ticket = " + ticket);
        } catch (TokenException e) {
            log.error("Generate ticket error", e);
        }
    }

}
