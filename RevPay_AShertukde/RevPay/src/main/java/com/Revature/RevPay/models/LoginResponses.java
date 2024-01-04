package com.Revature.RevPay.models;

public class LoginResponses {
    private String message;

    public LoginResponses(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
