package com.netheve.netter.common.configuration;

import com.netheve.netter.common.configuration.interceptor.PreflightInterceptor;
import com.netheve.netter.common.configuration.property.WebProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PreflightHandlerConfiguration implements WebMvcConfigurer {
    @Autowired
    private WebProperty webProperty;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PreflightInterceptor(webProperty))
                .addPathPatterns("/**");
    }
}
