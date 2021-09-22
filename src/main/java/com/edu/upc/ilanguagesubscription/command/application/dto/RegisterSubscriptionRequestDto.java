package com.edu.upc.ilanguagesubscription.command.application.dto;

public class RegisterSubscriptionRequestDto {
    private int subscriptionId;
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

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
