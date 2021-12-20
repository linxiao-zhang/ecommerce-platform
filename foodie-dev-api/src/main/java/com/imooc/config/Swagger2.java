package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    //http://localhost:8088/swagger-ui.html 原路径
    //http://localhost:8088/doc.html 原路径


    //配置swagger2核心配置 docket
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.imooc.controller"))
                .paths(PathSelectors.any()) //所有controller
                .build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("电商平台接口api")        //文档页标题
                .contact(new Contact("zlx","https://www.imooc.com","zhanglinxiao77@gmail.com"))
                .description("为电商商城提供的api文档")
                .version("1.0.1")  //文档版本号
                .termsOfServiceUrl("https://www.imooc.com")
                .build();
    }




}
