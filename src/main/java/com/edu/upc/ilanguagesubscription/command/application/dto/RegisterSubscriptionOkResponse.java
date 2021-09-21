package com.edu.upc.ilanguagesubscription.command.application.dto;

public class RegisterSubscriptionOkResponse {
    private int subscriptionId;

    public RegisterSubscriptionOkResponse(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
    public int getSubscriptionId() {
        return subscriptionId;
    }
}
