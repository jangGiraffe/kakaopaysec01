package com.kakaopaysec.common.config;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kakaopaysec.vo.UserVo;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SweggerConfig {
	
	private static final String API_TITLE = "카카오페이증권 과제1_SpringBoot_REST_API";
	private static final String API_DESCRIPTION = "<h3>■REST API 요청 시 헤더에 다음 값이 포함되야 합니다.</h3>Content-Type : application/json<br>key : 토큰을 발급한 key<br>token : 발급받은 token";
	private static final String API_VERSION = "1.0";
	
    private ApiInfo commonInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .build();
    }

    @Bean
    public Docket allApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(API_TITLE)
                .alternateTypeRules(AlternateTypeRules.newRule(getClass(), UserVo.class))
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kakaopaysec"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(commonInfo());
    }
}
