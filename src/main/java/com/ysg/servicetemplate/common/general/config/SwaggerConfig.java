package com.ysg.servicetemplate.common.general.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ysg.servicetemplate.api"))
                .paths(PathSelectors.any())
                .paths(PathSelectors.regex("/error.*").negate())
                .build()
                .apiInfo(new ApiInfoBuilder().title("template").build());
    }
}
