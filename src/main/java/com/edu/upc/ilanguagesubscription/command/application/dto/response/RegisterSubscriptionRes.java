package com.edu.upc.ilanguagesubscription.command.application.dto.response;

import lombok.Value;
import org.springframework.stereotype.Component;

@Value
public class RegisterSubscriptionRes {
    String subscriptionId;
    String name;
    int monthDuration;
    int price;

}
