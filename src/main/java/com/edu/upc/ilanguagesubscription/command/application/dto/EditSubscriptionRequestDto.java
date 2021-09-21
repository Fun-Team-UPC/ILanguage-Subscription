package com.edu.upc.ilanguagesubscription.command.application.dto;

public class EditSubscriptionRequestDto {
    private int subscriptionId;
    private String name;
    private float price;
    private int monthDuration;

    public String getName() {
        return name;
    }

    public float getPrice() {
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
