package com.xin.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xinyu.huang02
 * @date 2020-02-24 21:58
 * @class oauth application
 */

@SpringBootApplication
@MapperScan("com.xin.oauth.mapper")
@EnableSwagger2
public class OauthApplication {

    public static void main(String args[]) {
        SpringApplication.run(OauthApplication.class, args);
    }

}
