package com.edu.upc.ilanguagesubscription.command.application.dto.request;

public class EditSubscriptionRequestDto {
    private String subscriptionId;
    private String name;
    private int price;
    private int monthDuration;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getMonthDuration() {
        return monthDuration;
    }

    public String getSubgetSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
