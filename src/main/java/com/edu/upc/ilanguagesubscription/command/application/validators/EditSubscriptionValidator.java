package com.edu.upc.ilanguagesubscription.command.application.validators;

import com.edu.upc.ilanguagesubscription.command.application.dto.request.EditSubscriptionRequestDto;
import com.edu.upc.ilanguagesubscription.command.domain.Subscription;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfra;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfraRepository;
import com.edu.upc.ilanguagesubscription.common.application.Notification;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class EditSubscriptionValidator {
    private final SubscriptionInfraRepository _subscriptionRepository;
    private final Repository<Subscription> subscriptionRepository;

    public Notification validate(EditSubscriptionRequestDto editSubscriptionRequestDto){
        Notification notification = new Notification();
        String name = editSubscriptionRequestDto.getName();
        int monthDuration = editSubscriptionRequestDto.getMonthDuration();
        int price = editSubscriptionRequestDto.getPrice();

        //First of all, id should be valid
        Optional<SubscriptionInfra> existingSubscription = _subscriptionRepository.findSubscriptionBySubscriptionId(editSubscriptionRequestDto.getSubgetSubscriptionId());
        if(existingSubscription.isEmpty()){
            notification.addError("Subscription with id" + editSubscriptionRequestDto.getSubgetSubscriptionId() + " does not exists");
            return notification;
        }
        existingSubscription = _subscriptionRepository.findByPrice(editSubscriptionRequestDto.getPrice());
        if(existingSubscription.isPresent()){
            notification.addError("Subscription with that price already exists");
        }
        existingSubscription = _subscriptionRepository.findByMonthDuration(editSubscriptionRequestDto.getMonthDuration());
        if(existingSubscription.isPresent()){
            notification.addError("Subscription with that duration already exists");
        }
        existingSubscription = _subscriptionRepository.findByName(editSubscriptionRequestDto.getName());
        if(existingSubscription.isPresent()){
            notification.addError("Subscription with that name already exists");
        }
        return notification;

    }

    private void loadSubscriptionAggregate(String subscriptionId){
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            subscriptionRepository.load(subscriptionId);
            unitOfWork.commit();
        }
        catch(AggregateNotFoundException ex){
            unitOfWork.commit();
            throw ex;
        }
        catch (Exception ex){
            if(unitOfWork != null) unitOfWork.rollback();
        }
    }
}
