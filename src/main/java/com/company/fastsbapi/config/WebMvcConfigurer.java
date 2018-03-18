package com.company.fastsbapi.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring MVC 配置
 */
@Configuration
@Slf4j
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" };

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods(ORIGINS);
    }

}
