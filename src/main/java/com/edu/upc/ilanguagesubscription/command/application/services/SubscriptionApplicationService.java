package com.edu.upc.ilanguagesubscription.command.application.services;

import com.edu.upc.ilanguagesubscription.command.application.dto.request.EditSubscriptionRequestDto;
import com.edu.upc.ilanguagesubscription.command.application.dto.request.RegisterSubscriptionRequest;
import com.edu.upc.ilanguagesubscription.command.application.dto.response.EditSubscriptionOkResponse;
import com.edu.upc.ilanguagesubscription.command.application.dto.response.RegisterSubscriptionRes;
import com.edu.upc.ilanguagesubscription.command.application.validators.EditSubscriptionValidator;
import com.edu.upc.ilanguagesubscription.command.application.validators.RegisterSubscriptionValidator;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfraRepository;
import contracts.commands.EditSubscription;
import contracts.commands.RegisterSubscription;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import pe.edu.upc.banking.common.application.Notification;
import pe.edu.upc.banking.common.application.Result;
import pe.edu.upc.banking.common.application.ResultType;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class SubscriptionApplicationService {
    private final RegisterSubscriptionValidator _registrationValidator;
    private final CommandGateway _commandGateway;
    private final SubscriptionInfraRepository _subscriptionRepository;
    private final EditSubscriptionValidator _editValidator;

    public Result<RegisterSubscriptionRes, Object> register(RegisterSubscriptionRequest registerSubscriptionRequest) throws Exception{
        Notification notification = this._registrationValidator.validate(registerSubscriptionRequest);
        if(notification.hasErrors()){
            return Result.failure(notification);
        }
        String subscriptionId = UUID.randomUUID().toString();
        RegisterSubscription registerSubscription = new RegisterSubscription(
                subscriptionId,
                registerSubscriptionRequest.getName().trim(),
                registerSubscriptionRequest.getMonthDuration(),
                registerSubscriptionRequest.getPrice(),
                Instant.now()
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

    public Result<EditSubscriptionOkResponse, Notification> edit(EditSubscriptionRequestDto editSubscriptionRequestDto) throws Exception{
        Notification notification = this._editValidator.validate(editSubscriptionRequestDto);
        if(notification.hasErrors()){
            return Result.failure(notification);
        }
        EditSubscription editSubscription = new EditSubscription(
                editSubscriptionRequestDto.getSubgetSubscriptionId(),
                editSubscriptionRequestDto.getName(),
                editSubscriptionRequestDto.getMonthDuration(),
                editSubscriptionRequestDto.getPrice()
        );

        CompletableFuture<Object> future = _commandGateway.send(editSubscription);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE :  ResultType.SUCCESS);

        ResultType resultType = futureResult.get();
        if(resultType == ResultType.FAILURE){
            throw new Exception();
        }
        EditSubscriptionOkResponse editSubscriptionOkResponse = new EditSubscriptionOkResponse(
                editSubscription.getSubscriptionId(),
                editSubscription.getName(),
                editSubscription.getMonthDuration(),
                editSubscription.getPrice()
        );
        return Result.success(editSubscriptionOkResponse);
    }
}
