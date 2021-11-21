package com.edu.upc.ilanguagesubscription.command.api;

import com.edu.upc.ilanguagesubscription.command.application.dto.request.EditSubscriptionRequestDto;
import com.edu.upc.ilanguagesubscription.command.application.dto.response.EditSubscriptionOkResponse;
import com.edu.upc.ilanguagesubscription.command.application.dto.request.RegisterSubscriptionRequest;
import com.edu.upc.ilanguagesubscription.command.application.dto.response.RegisterSubscriptionRes;
import com.edu.upc.ilanguagesubscription.command.application.services.SubscriptionApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.banking.common.api.ApiController;
import pe.edu.upc.banking.common.application.Notification;
import pe.edu.upc.banking.common.application.Result;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subscriptions")

public class SubscriptionCommandController {

    private final  CommandGateway _commandGateway;
    private final SubscriptionApplicationService _subscriotionService;

    public SubscriptionCommandController(CommandGateway _commandGateway, SubscriptionApplicationService _subscriotionService) {
        this._commandGateway = _commandGateway;
        this._subscriotionService = _subscriotionService;
    }

    @Operation(summary="Save subscription", description="This endpoint is for saving a new subscription for Ilanguage Application", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription registered", content = @Content(mediaType = "application/json",
                    schema = @Schema( example = "{\"id\": \"c1a4dd5a-f49c-46cb-b\",\"price\": 99.90, \"monthDuration\" : 5, \"name\" : \"Full All Year\", \"createdAt\" : 1636403644.048834 , \"updatedAt\": 3936403644.048834 }")
            )),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Subscription Not Found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Unexpected system error", content = @Content())

    })
    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterSubscriptionRequest registerSubscriptionRequestDto) {
        List<Error> errors = new ArrayList<>();
        try {
            Result<RegisterSubscriptionRes, Object> result = _subscriotionService.register(registerSubscriptionRequestDto);
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
