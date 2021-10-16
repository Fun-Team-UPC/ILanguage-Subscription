package com.edu.upc.ilanguagesubscription.query.projections;

import contracts.events.SubscriptionEdited;
import contracts.events.SubscriptionRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SubscriptionHistoryViewProjection {

    private final SubscriptionHistoryViewRepository _subscriptionRepository;

    public SubscriptionHistoryViewProjection(SubscriptionHistoryViewRepository _subscriptionRepository) {
        this._subscriptionRepository = _subscriptionRepository;
    }

    @EventHandler
    public void on (SubscriptionRegistered event, @Timestamp Instant timestamp) {
        SubscriptionHistoryView subscriptionView = new SubscriptionHistoryView(
                event.getSubscriptionId(),
                event.getPrice(),
                event.getMonthDuration(),
                event.getName(),
                event.getOccurredOn());
        _subscriptionRepository.save(subscriptionView);
    }

    @EventHandler
    public void on(SubscriptionEdited event, @Timestamp Instant timestamp) {
        SubscriptionHistoryView subscriptionHistoryView = new SubscriptionHistoryView(event.getSubscriptionId(),
                event.getPrice(),
                event.getMonthDuration(),
                event.getName(),
                event.getOccurredOn());
        _subscriptionRepository.save(subscriptionHistoryView);
    }
}
