package com.netheve.netter.common.configuration;

import com.netheve.netter.common.configuration.property.WebProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Autowired
    private WebProperty webProperty;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all endpoints, you can customize this path if needed
                .allowedMethods("*")
                .allowedOrigins(webProperty.getAdminPanelUrl());
    }
}
