package com.netheve.netter.common.configuration.interceptor;

import com.netheve.netter.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

public class SimpleTokenInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    public SimpleTokenInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (request.getMethod().equalsIgnoreCase("OPTIONS"))
            return true;

        String rawToken = request.getHeader("Authorization");
        if(rawToken == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");

        String authToken = rawToken.replace("Bearer ", "");

        if(!authService.validateToken(authToken))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");

        response.setStatus(HttpServletResponse.SC_OK);
        return true;
    }

}
