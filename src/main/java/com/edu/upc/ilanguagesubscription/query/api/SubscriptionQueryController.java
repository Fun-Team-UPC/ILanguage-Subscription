package com.edu.upc.ilanguagesubscription.query.api;


import com.edu.upc.ilanguagesubscription.query.projections.SubscriptionHistoryView;
import com.edu.upc.ilanguagesubscription.query.projections.SubscriptionHistoryViewRepository;
import com.edu.upc.ilanguagesubscription.query.projections.SubscriptionView;
import com.edu.upc.ilanguagesubscription.query.projections.SubscriptionViewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/subscriptions")

public class SubscriptionQueryController {

    private final SubscriptionViewRepository _subscriptionRepository;
    private final SubscriptionHistoryViewRepository _subscriptionHistoryRepository;

    public SubscriptionQueryController(SubscriptionViewRepository _subscriptionRepository, SubscriptionHistoryViewRepository _subscriptionHistoryRepository) {
        this._subscriptionRepository = _subscriptionRepository;
        this._subscriptionHistoryRepository = _subscriptionHistoryRepository;
    }

    @Operation(summary="Get all subscriptions", description="This endpoind returns all the availeble subscription for Ilanguage Application", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All subscriptions returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Subscription Not Found", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("")
    public ResponseEntity<List<SubscriptionView>> getAll(){
        try {
            return new ResponseEntity<List<SubscriptionView>>(_subscriptionRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary="Get subscription by id", description="This endpoind returns an specific subscription by the given ID Ilanguage Application", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="Subscription returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Subscription Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping(value= "id/{id}", method = RequestMethod.GET)
    public ResponseEntity<SubscriptionView> getById(@PathVariable(name="id") String subscriptionId){
        try {
            return new ResponseEntity<SubscriptionView>(_subscriptionRepository.getById(subscriptionId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary="Get subscription by name", description="This endpoind returns an specific subscription by the given ID Ilanguage Application", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="Subscription returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Subscription Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping( value = "name/{name}",method = RequestMethod.GET)
    public ResponseEntity<SubscriptionView> getByName(@PathVariable(name="name") String subscriptionName){
        try {
            return new ResponseEntity<SubscriptionView>(_subscriptionRepository.findByName(subscriptionName), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary="Get history subscription by id", description="This endpoind returns the list with the history of an specific subscription by the given ID Ilanguage Application", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="Subscription returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Subscription Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping(value= "historyid/{historyid}", method = RequestMethod.GET)
    public ResponseEntity<List<SubscriptionHistoryView>> getHistoryById(@PathVariable(name="historyid") String subscriptionId){
        try {
            return new ResponseEntity<List<SubscriptionHistoryView>>(_subscriptionHistoryRepository.getSubscriptionHistoryById(subscriptionId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
