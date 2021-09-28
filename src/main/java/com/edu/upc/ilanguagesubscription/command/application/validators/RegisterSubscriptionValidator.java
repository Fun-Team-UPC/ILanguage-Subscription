package com.edu.upc.ilanguagesubscription.command.application.validators;

import com.edu.upc.ilanguagesubscription.command.application.dto.request.RegisterSubscriptionRequest;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfra;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pe.com.ilanguage.common.application.Notification;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RegisterSubscriptionValidator {
    private final SubscriptionInfraRepository _subscriptionRepository;

    public Notification validate(RegisterSubscriptionRequest registerSubscriptionRequestDto){
        Notification notification = new Notification();
        String name = registerSubscriptionRequestDto.getName();
        int monthDuration = registerSubscriptionRequestDto.getMonthDuration();
        int price = registerSubscriptionRequestDto.getPrice();
        //Validate if empty
        if(name.isEmpty()){
            notification.addError("Subscription name is required");
        }
        if(monthDuration <=0){
            notification.addError("Duration can not be negative");
        }
        if(price <= 0){
            notification.addError("Price can not be negative");
        }

        Optional<SubscriptionInfra> existingSubscription = _subscriptionRepository.findByName(name);
        if(existingSubscription.isPresent()){
            notification.addError("Subscription with thath name already exists");
        }
        existingSubscription = _subscriptionRepository.findByPrice(price);
        if(existingSubscription.isPresent()){
            notification.addError("Subscription with that price already exists");
        }
        existingSubscription = _subscriptionRepository.findByMonthDuration(monthDuration);
        if(existingSubscription.isPresent()){
            notification.addError("Subscription with that duration already exists");
        }

        return notification;
    }

}
