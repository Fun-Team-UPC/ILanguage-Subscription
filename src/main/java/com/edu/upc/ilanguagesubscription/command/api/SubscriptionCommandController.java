package com.edu.upc.ilanguagesubscription.command.api;

import com.edu.upc.ilanguagesubscription.command.application.dto.RegisterSubscriptionErrorResponse;
import com.edu.upc.ilanguagesubscription.command.application.dto.RegisterSubscriptionOkResponse;
import com.edu.upc.ilanguagesubscription.command.application.dto.RegisterSubscriptionRequestDto;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfra;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionRepository;
import io.swagger.annotations.Api;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/subscriptions")
@Api(tags = "Customers")
public class SubscriptionCommandController {
    private final CommandGateway _commandGateway;
    private final SubscriptionRepository _subscriptionRepository;


    public SubscriptionCommandController(CommandGateway commandGateway, SubscriptionRepository subscriptionRepository) {
        _commandGateway = commandGateway;
        _subscriptionRepository = subscriptionRepository;
    }

    @PostMapping("")
    public ResponseEntity<Object> register(@RequestBody RegisterSubscriptionRequestDto registerSubscriptionRequestDto) {
        Optional<SubscriptionInfra> existingSubscriptionInfra = _subscriptionRepository.findById(registerSubscriptionRequestDto.getSubscriptionId());
        if (existingSubscriptionInfra.isPresent()) {
            return new ResponseEntity(new RegisterSubscriptionErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        int subscriptionId = Integer.parseInt(UUID.randomUUID().toString());

        RegisterSubscription registerSubscription = new RegisterSubscription(
                subscriptionId,
                registerSubscriptionRequestDto.getName(),
                registerSubscriptionRequestDto.getPrice(),
                registerSubscriptionRequestDto.getMonthDuration()
        );
        CompletableFuture<Object> future = _commandGateway.send(registerSubscription);
        CompletableFuture<Object> futureResponse = future.handle((ok, ex) -> {
            if (ex != null) {
                return new RegisterSubscriptionErrorResponse();
            }
            return new RegisterSubscriptionOkResponse(subscriptionId);
        });

        Object response = null;
        try {
            response = (Object) futureResponse.get();
            if (response instanceof RegisterSubscriptionOkResponse) {
                return new ResponseEntity(response, HttpStatus.CREATED);
            }
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
