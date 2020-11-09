package com.ygl.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author ygl
 * @description
 * @date 2020/11/9 10:45
 */
@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {

    //配置swagger的Docker的bean实例
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("a");
    }
    //配置swagger的Docker的bean实例
    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("B");
    }

    //配置swagger的Docker的bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
//                .enable(false)
                .groupName("ygl")
                .select()
                //RequestHandlerSelectors配置扫描接口的方式
                //basePackage：指定的包
                //any()  任意的接口
                //None()   不扫描接口
                .apis(RequestHandlerSelectors.any())
                //path()过滤生命路径   只扫描带hello下面的东西
//                .paths(PathSelectors.ant("/hell/**"))
                .build();
    }
    //配置Swagger信息的ApiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("ygl", "www.baidu.com", "klsjsjs.com");
        return new ApiInfo(
                "闫高岭的API文档",
                "好好学习，天天向上",
                "1.20",
                "dkhushfudshf.com",
                contact,
                "Apache 2.0",
                "http://hduashfues.com",
                new ArrayList<>()
        );
    }
}
