package com.edu.upc.ilanguagesubscription.command.application.dto.response;

import lombok.Value;

@Value
public class EditSubscriptionOkResponse {
    String subscriptionId;
    String name;
    int monthDuration;
    int price;
}
