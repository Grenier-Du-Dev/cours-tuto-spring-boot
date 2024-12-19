package com.blog.app.shared.exceptions;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException, IOException {

        String fullUrl = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        if (queryString != null) {
            fullUrl += "?" + queryString;
        }
        System.out.println("fullUrl : " + fullUrl);
        System.out.println("method : " + request.getMethod());
        System.out.println("accessDeniedException : " + accessDeniedException.getMessage());

        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpServletResponse.SC_FORBIDDEN);
        request.setAttribute("errorMessage", "Access Denied: " + accessDeniedException.getMessage());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
        dispatcher.forward(request, response);
    }
}