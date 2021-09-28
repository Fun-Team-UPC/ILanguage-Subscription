package com.edu.upc.ilanguagesubscription.command.application.handlers;


import com.edu.upc.ilanguagesubscription.command.domain.contracts.events.SubscriptionRegistered;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfra;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfraRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("subscriptionInfra")
public class SubscriptionEventHandler {

    private final SubscriptionInfraRepository _subscriptionRepository;

    public SubscriptionEventHandler(SubscriptionInfraRepository _subscriptionRepository) {
        this._subscriptionRepository = _subscriptionRepository;
    }

    @EventHandler
    public void on(SubscriptionRegistered event) {
        SubscriptionInfra _subscriptionInfra = new SubscriptionInfra();
        _subscriptionInfra.setId(event.getSubscriptionId());
        _subscriptionInfra.setMonthDuration(event.getMonthDuration());
        _subscriptionInfra.setName(event.getName());
        _subscriptionInfra.setPrice(event.getPrice());
        _subscriptionRepository.save(_subscriptionInfra);
    }
}
-