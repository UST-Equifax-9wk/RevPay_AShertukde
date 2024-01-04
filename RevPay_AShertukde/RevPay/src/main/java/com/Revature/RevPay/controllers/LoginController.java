package com.Revature.RevPay.controllers;

import com.Revature.RevPay.models.LoginResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class LoginController {
    @GetMapping("/failure")
    public LoginResponses loginfail()
    {
        return new LoginResponses("error");
    }
    @GetMapping("/success")
    public LoginResponses loginsuccess()
    {
        return new LoginResponses("success");
    }
    @GetMapping("/custom-logout")
    public LoginResponses logout()
    {
        return new LoginResponses("success");
    }
    @GetMapping("/login")
    public LoginResponses login()
    {
        return new LoginResponses("login");
    }
}
