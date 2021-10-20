package com.edu.upc.ilanguagesubscription.command.api;

import com.edu.upc.ilanguagesubscription.command.application.dto.request.EditSubscriptionRequestDto;
import com.edu.upc.ilanguagesubscription.command.application.dto.response.EditSubscriptionOkResponse;
import com.edu.upc.ilanguagesubscription.command.application.dto.response.RegisterSubscriptionRes;
import com.edu.upc.ilanguagesubscription.command.application.dto.request.RegisterSubscriptionRequest;
import com.edu.upc.ilanguagesubscription.command.application.services.SubscriptionApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.ilanguage.common.api.ApiController;
import pe.com.ilanguage.common.application.Error;
import pe.com.ilanguage.common.application.Notification;
import pe.com.ilanguage.common.application.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subscriptions")

public class SubscriptionCommandController {
    private final CommandGateway _commandGateway;
    private final SubscriptionApplicationService _subscriotionService;

    public SubscriptionCommandController(CommandGateway _commandGateway, SubscriptionApplicationService _subscriotionService) {
        this._commandGateway = _commandGateway;
        this._subscriotionService = _subscriotionService;
    }

    @Operation(summary="Save subscription", description="This endpoint is for saving a new subscription for Ilanguage Application", tags = {"subscriptions"} )
    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterSubscriptionRequest registerSubscriptionRequestDto) {
        List<Error> errors = new ArrayList<>();
        try {
            Result<RegisterSubscriptionRes, Notification> result = _subscriotionService.register(registerSubscriptionRequestDto);
            if(result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            //TODO: this return is not ok
            return ApiController.serverError();
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @Operation(summary="Edit subscription", description="This endpoind is for editing an existing subscription in Ilanguage Application", tags = {"subscriptions"} )
    @PutMapping("/{subscriptionId}")
    public ResponseEntity<Object>edit(@PathVariable("subscriptionId") String subscriptionId, @RequestBody EditSubscriptionRequestDto editSubscriptionRequestDto){
        try {
            editSubscriptionRequestDto.setSubscriptionId(subscriptionId);
            Result<EditSubscriptionOkResponse, Notification> result = _subscriotionService.edit(editSubscriptionRequestDto);
            if(result.isSuccess()){
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.notFound();
        } catch (AggregateNotFoundException exception){
            return ApiController.notFound();
        } catch (Exception e) {
            return ApiController.serverError();
        }

    }
}
