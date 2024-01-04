package com.Revature.RevPay.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        addcookie(response, authentication);
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    private void addcookie(HttpServletResponse response, Authentication authentication) {
        Cookie cookie = new Cookie("username", authentication.getName());
        cookie.setMaxAge(60*10);
        cookie.setPath("/");
        response.addCookie(cookie);
        cookie = new Cookie("authorities", authentication.getAuthorities().toString());
        cookie.setMaxAge(60*10);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        addcookie(response, authentication);
        response.sendRedirect("/success");
    }
}
