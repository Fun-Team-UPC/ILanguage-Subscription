package com.edu.upc.ilanguagesubscription.command.domain;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;


import contracts.commands.EditSubscription;
import contracts.commands.RegisterSubscription;
import contracts.events.SubscriptionEdited;
import contracts.events.SubscriptionRegistered;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

@Aggregate
@Getter
public class Subscription {
    @AggregateIdentifier
    private String subscriptionId;
    private int price;
    private int monthDuration;
    private String name;


    public Subscription(){}

    @CommandHandler
    public Subscription(RegisterSubscription command) {
//        Instant now = Instant.now();
        apply(
                new SubscriptionRegistered(
                        command.getSubscriptionId(),
                        command.getName(),
                        command.getMonthDuration(),
                        command.getPrice()
                )
        );
    }

    @CommandHandler
    public void handle (RegisterSubscription command) {
        Instant now = Instant.now();
        apply(
                new SubscriptionEdited(
                        command.getSubscriptionId(),
                        command.getName(),
                        command.getMonthDuration(),
                        command.getPrice(),
                        now
                )
        );
    }

    @EventSourcingHandler
    public void  on(SubscriptionRegistered event) {
        subscriptionId =    event.getSubscriptionId();
        name = event.getName();
        price = event.getPrice();
        monthDuration = event.getMonthDuration();
    }

    @EventSourcingHandler
    protected void on(SubscriptionEdited event){
        name  = event.getName();
        monthDuration = event.getMonthDuration();
        price = event.getPrice();
    }

}
