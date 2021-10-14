package com.edu.upc.ilanguagesubscription.command.application.dto;

public class RegisterSubscriptionErrorResponse {
    private String message;

    public RegisterSubscriptionErrorResponse() {
        this.message = "Error Registerng Subscription";
    }

    public String getMessage() {
        return message;
    }
}
