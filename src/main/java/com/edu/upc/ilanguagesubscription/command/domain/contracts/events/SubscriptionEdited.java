package com.edu.upc.ilanguagesubscription.command.domain.contracts.events;

import lombok.Value;

import java.time.Instant;

@Value
public class SubscriptionEdited {
    int subscriptionId;
    String name;
    int monthDuration;
    int price;
    Instant occurredOn;
}
