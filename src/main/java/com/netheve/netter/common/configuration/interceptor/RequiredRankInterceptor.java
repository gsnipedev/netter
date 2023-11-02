package com.netheve.netter.common.configuration.interceptor;

import com.netheve.netter.entity.enums.AccountRank;
import com.netheve.netter.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;


public class RequiredRankInterceptor implements HandlerInterceptor {
    private final AuthService authService;
    private final AccountRank requiredRank;

    public RequiredRankInterceptor(AuthService authService, AccountRank requiredRank){
        this.authService = authService;
        this.requiredRank = requiredRank;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String rawToken = request.getHeader("Authorization");
        if(rawToken == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");

        if(!authService.validTokenAs(rawToken.replace("Bearer ", ""), requiredRank))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");

        response.setStatus(HttpServletResponse.SC_OK);
        return true;
    }
}
