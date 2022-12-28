//package com.company.banko.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class SwaggerConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
//
//    @Value("${prop.swagger.enabled:false}")
//    private boolean enableSwagger;
//
//    @Bean
//    public Docket SwaggerConfig() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(enableSwagger)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.your.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        if (enableSwagger)
//            web.ignoring().antMatchers("/v2/api-docs",
//                    "/configuration/ui",
//                    "/swagger-resources/**",
//                    "/configuration/security",
//                    "/swagger-ui.html",
//                    "/webjars/**");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if (enableSwagger) {
//            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        }
//    }
//}