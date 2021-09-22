package com.edu.upc.ilanguagesubscription.command.application.dto;

public class RegisterSubscriptionRequestDto {
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

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
