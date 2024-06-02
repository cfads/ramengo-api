package com.example.ramengo.interceptor;

import com.example.ramengo.dtos.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    private static final String API_KEY = "6L8UhCLzzcI9EFZBEoGFbqgAAvlEQT6ZlN679_UANHY";

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader("x-api-key");

        if (apiKey == null || apiKey.isEmpty()) {
            returnErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, "x-api-key header missing");
            return false;
        }

        if (API_KEY.equals(apiKey)) {
            return true;
        } else {
            returnErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, "Invalid API key");
            return false;
        }
    }

    private void returnErrorResponse(HttpServletResponse response, int status, String errorMessage) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(errorMessage);

        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}

