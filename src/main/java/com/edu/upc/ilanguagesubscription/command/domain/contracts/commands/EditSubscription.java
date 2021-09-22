package com.edu.upc.ilanguagesubscription.command.domain.contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Value
public class EditSubscription {
    @TargetAggregateIdentifier
    int subscriptionId;
    String name;
    int monthDuration;
    int price;
}
