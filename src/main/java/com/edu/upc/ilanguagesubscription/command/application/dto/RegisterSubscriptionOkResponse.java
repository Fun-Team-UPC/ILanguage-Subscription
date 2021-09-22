package com.edu.upc.ilanguagesubscription.command.application.dto;

public class RegisterSubscriptionOkResponse {
    private String subscriptionId;

    public RegisterSubscriptionOkResponse(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
    public String getSubscriptionId() {
        return subscriptionId;
    }
}
