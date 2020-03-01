package com.xin.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xinyu.huang02
 * @date 2020-02-29 13:22
 * @class Swagger相关配置类
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String SWAGGER_BASE_PACKAGE = "com.xin.oauth.controller";

    /**
     * 创建API应用
     * APIInfo() 增加了API相关信息
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 创建API信息（这些基本信息会展示在文档页面中）
     * visit url: http://项目实际地址/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Oauth 2.0 RESTFul APIs")
                .description("")
                .termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();
    }

}
