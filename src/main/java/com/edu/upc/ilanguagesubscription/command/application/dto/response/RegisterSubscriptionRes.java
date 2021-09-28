package com.edu.upc.ilanguagesubscription.command.application.dto.response;

public class RegisterSubscriptionRes {
    private String subscriptionId;

    public RegisterSubscriptionRes(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
    public String getSubscriptionId() {
        return subscriptionId;
    }
}
