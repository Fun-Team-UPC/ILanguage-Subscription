package com.edu.upc.ilanguagesubscription.command.application.services;

import com.edu.upc.ilanguagesubscription.command.application.dto.request.RegisterSubscriptionRequest;
import com.edu.upc.ilanguagesubscription.command.application.dto.response.RegisterSubscriptionRes;
import com.edu.upc.ilanguagesubscription.command.application.validators.RegisterSubscriptionValidator;
import com.edu.upc.ilanguagesubscription.command.domain.contracts.commands.RegisterSubscription;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfraRepository;
import com.edu.upc.ilanguagesubscription.common.application.Notification;
import com.edu.upc.ilanguagesubscription.common.application.Result;
import com.edu.upc.ilanguagesubscription.common.application.ResultType;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class SubscriptionApplicationService {
    private final RegisterSubscriptionValidator _registrationValidator;
    private final CommandGateway _commandGateway;
    private final SubscriptionInfraRepository _subscriptionRepository;

    public Result<RegisterSubscriptionRes, Notification> register(RegisterSubscriptionRequest registerSubscriptionRequest) throws Exception{
        Notification notification = this._registrationValidator.validate(registerSubscriptionRequest);
        if(notification.hasErrors()){
            return Result.failure(notification);
        }
        String subscriptionId = UUID.randomUUID().toString();
        RegisterSubscription registerSubscription = new RegisterSubscription(
                subscriptionId,
                registerSubscriptionRequest.getName().trim(),
                registerSubscriptionRequest.getMonthDuration(),
                registerSubscriptionRequest.getPrice()
        );

        CompletableFuture<Object> future = _commandGateway.send(registerSubscription);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null)? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if(resultType == ResultType.FAILURE){
            throw new Exception();
        }

        RegisterSubscriptionRes registerSubsciptionResponseDto = new RegisterSubscriptionRes(
            registerSubscription.getSubscriptionId(),
                registerSubscription.getName(),
                registerSubscription.getMonthDuration(),
                registerSubscription.getPrice()
        );
        return Result.success(registerSubsciptionResponseDto);
    }
}
