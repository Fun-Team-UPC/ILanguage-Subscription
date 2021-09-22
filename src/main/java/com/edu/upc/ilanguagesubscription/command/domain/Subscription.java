package com.edu.upc.ilanguagesubscription.command.domain;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.edu.upc.ilanguagesubscription.command.domain.contracts.commands.EditSubscription;
import com.edu.upc.ilanguagesubscription.command.domain.contracts.commands.RegisterSubscription;
import com.edu.upc.ilanguagesubscription.command.domain.contracts.events.SubscriptionEdited;
import com.edu.upc.ilanguagesubscription.command.domain.contracts.events.SubscriptionRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

@Aggregate
public class Subscription {
    @AggregateIdentifier
    private int subscriptionId;
    private int price;
    private int monthDuration;
    private String name;


    public Subscription(){}

    @CommandHandler
    public Subscription(RegisterSubscription command) {
        Instant now = Instant.now();
        apply(
                new SubscriptionRegistered(
                        command.getSubscriptionId(),
                        command.getName(),
                        command.getMonthDuration(),
                        command.getPrice(),
                        now
                )
        );
    }

    @CommandHandler
    public void handle (EditSubscription command) {
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
    protected void  on(SubscriptionRegistered event) {
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
