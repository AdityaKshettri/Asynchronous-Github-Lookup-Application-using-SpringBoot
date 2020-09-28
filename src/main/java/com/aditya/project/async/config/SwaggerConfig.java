package com.aditya.project.async.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket todoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("REST-APIs")
                .apiInfo(apiInfo())
                .select()
                .paths(postPaths())
                .build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/users.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Github Lookup Rest APIs")
                .description("API reference for User Service")
                .contact(new Contact("Aditya Kshettri", "https://github.com/AdityaKshettri", "adikshettri1623@gmail.com"))
                .version("1.0")
                .build();
    }
}

