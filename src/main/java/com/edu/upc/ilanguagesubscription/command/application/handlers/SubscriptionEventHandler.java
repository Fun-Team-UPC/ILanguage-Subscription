package com.edu.upc.ilanguagesubscription.command.application.handlers;



import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfra;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfraRepository;
import contracts.events.SubscriptionEdited;
import contracts.events.SubscriptionRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    @EventHandler
    public void on(SubscriptionEdited event){
        Optional<SubscriptionInfra> _subscriptionInfra = _subscriptionRepository.findSubscriptionBySubscriptionId(event.getSubscriptionId());
        _subscriptionInfra.ifPresent(_subscriptionRepository::delete);
        SubscriptionInfra newSubscription =  new SubscriptionInfra();
        newSubscription.setId(event.getSubscriptionId());
        newSubscription.setName(event.getName());
        newSubscription.setMonthDuration(event.getMonthDuration());
        newSubscription.setPrice(event.getPrice());
        _subscriptionRepository.save(newSubscription);
    }
}
