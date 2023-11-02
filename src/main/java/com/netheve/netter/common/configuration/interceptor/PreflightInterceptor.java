package com.netheve.netter.common.configuration.interceptor;

import com.netheve.netter.common.configuration.property.WebProperty;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class PreflightInterceptor implements HandlerInterceptor {
    private final WebProperty webProperty;

    public PreflightInterceptor(WebProperty webProperty){
        this.webProperty = webProperty;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setHeader("Access-Control-Allow-Origin", webProperty.getAdminPanelUrl());
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE");
        }

        return true;
    }
}
