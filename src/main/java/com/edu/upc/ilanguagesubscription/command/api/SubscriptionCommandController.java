package com.edu.upc.ilanguagesubscription.command.api;

import com.edu.upc.ilanguagesubscription.command.application.dto.request.EditSubscriptionRequestDto;
import com.edu.upc.ilanguagesubscription.command.application.dto.response.EditSubscriptionOkResponse;
import com.edu.upc.ilanguagesubscription.command.application.dto.response.RegisterSubscriptionRes;
import com.edu.upc.ilanguagesubscription.command.application.dto.request.RegisterSubscriptionRequest;
import com.edu.upc.ilanguagesubscription.command.application.services.SubscriptionApplicationService;
import com.edu.upc.ilanguagesubscription.command.domain.contracts.commands.RegisterSubscription;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfra;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfraRepository;
import com.edu.upc.ilanguagesubscription.common.api.ApiController;
import com.edu.upc.ilanguagesubscription.common.application.Notification;
import com.edu.upc.ilanguagesubscription.common.application.Result;
import io.swagger.annotations.Api;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/*import pe.com.ilanguage.common.application.Result;
import pe.com.ilanguage.common.application.Notification;
import pe.com.ilanguage.common.api.ApiController;
import pe.com.ilanguage.common.application.Error;*/
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@Api(tags = "Subscriptions")
public class SubscriptionCommandController {
    private final CommandGateway _commandGateway;
    private final SubscriptionApplicationService _subscriotionService;

    public SubscriptionCommandController(CommandGateway _commandGateway, SubscriptionApplicationService _subscriotionService) {
        this._commandGateway = _commandGateway;
        this._subscriotionService = _subscriotionService;
    }

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
