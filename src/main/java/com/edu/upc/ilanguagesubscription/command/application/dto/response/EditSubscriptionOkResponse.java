package com.edu.upc.ilanguagesubscription.command.application.dto.response;

public class EditSubscriptionOkResponse {
    private String subscriptionId;

    public EditSubscriptionOkResponse(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
