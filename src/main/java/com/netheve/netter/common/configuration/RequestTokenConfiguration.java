package com.netheve.netter.common.configuration;


import com.netheve.netter.common.configuration.interceptor.SimpleTokenInterceptor;
import com.netheve.netter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestTokenConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthService authService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SimpleTokenInterceptor(authService))
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/login");
    }
}
