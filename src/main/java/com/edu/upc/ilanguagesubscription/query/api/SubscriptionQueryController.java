package com.edu.upc.ilanguagesubscription.query.api;


import com.edu.upc.ilanguagesubscription.query.projections.SubscriptionView;
import com.edu.upc.ilanguagesubscription.query.projections.SubscriptionViewRepository;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@Api(tags = "Subscriptions")
public class SubscriptionQueryController {

    private final SubscriptionViewRepository _subscriptionRepository;

    public SubscriptionQueryController(SubscriptionViewRepository _subscriptionRepository) {
        this._subscriptionRepository = _subscriptionRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<SubscriptionView>> getAll(){
        try {
            return new ResponseEntity<List<SubscriptionView>>(_subscriptionRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
