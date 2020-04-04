package com.swagger.demo.config;

import com.swagger.demo.model.Person;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenheng
 * @time 2020/4/3 12:58
 */
@Configuration
public class Swagger2Config {

    public static final String TOKEN_NAME = "token";

    @Value("${server.port}")
    private int serverPort;

//    @Bean
//    public Docket createRestApi() {
//        List<ApiKey> security = Arrays.asList(new ApiKey(TOKEN_NAME, TOKEN_NAME, "header"));
//        return new Docket(DocumentationType.SWAGGER_2)//定义版本
//                .apiInfo(apiInfo())
//                .select()
////                .apis(RequestHandlerSelectors.basePackage("com.swagger.demo.controller"))
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build()
//                .securitySchemes(security)
//                .securityContexts(securityContexts());
//    }
//
//    private List<SecurityContext> securityContexts() {
//        List<SecurityContext> securityContexts = new ArrayList<>();
//        securityContexts.add(
//                SecurityContext.builder()
//                        .securityReferences(defaultAuth())
//                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
//                        .build());
//        return securityContexts;
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        List<SecurityReference> securityReferences = new ArrayList<>();
//        securityReferences.add(new SecurityReference(TOKEN_NAME, authorizationScopes));
//        return securityReferences;
//    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)//定义版本
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.swagger.demo.controller"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(globalOperation())
                .ignoredParameterTypes(Person.class);

    }


    private List<Parameter> globalOperation(){
        //添加head参数配置start
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        //第一个token为传参的key，第二个token为swagger页面显示的值
        parameterBuilder.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        pars.add(parameterBuilder.build());
        return pars;
    }

//    @Bean
//    public Docket createRestApiCore() {
//        return new Docket(DocumentationType.SWAGGER_2)//定义版本
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.swagger.demo.core.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .groupName("core");
//
//    }
//    @Bean
//    public Docket createRestApiCommon() {
//        return new Docket(DocumentationType.SWAGGER_2)//定义版本
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.swagger.demo.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .groupName("common");
//
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger标题")
                .description("swagger描述")
                //swagger地址
                .termsOfServiceUrl("http://localhost:" + serverPort + "/swagger-ui.html")
                .contact(new Contact("联系人", "联系地址", "联系email"))
                .version("1.1")//定义版本号
                .build();
    }
}
