package com.edu.upc.ilanguagesubscription.command.application.dto;

public class EditSubscriptionOkResponseDto {
    private String subscriptionId;

    public EditSubscriptionOkResponseDto(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
