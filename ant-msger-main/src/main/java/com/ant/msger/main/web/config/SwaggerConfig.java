//package com.ant.msger.main.web.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * Created by Alan on 2017/5/22.
// */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket customImplementation() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.ant.msger.main.web.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("JT-808-API")
//                .description("API")
//                .termsOfServiceUrl("")
//                .license("")
//                .licenseUrl("")
//                .version("1.0.0")
//                .build();
//    }
//}