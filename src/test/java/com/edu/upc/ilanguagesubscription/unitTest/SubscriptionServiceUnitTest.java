package com.edu.upc.ilanguagesubscription.unitTest;



import com.edu.upc.ilanguagesubscription.command.domain.Subscription;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import ILanguage.subscriptions.contracts.commands.RegisterSubscription;
import ILanguage.subscriptions.contracts.events.SubscriptionRegistered;


import java.time.Instant;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
public class SubscriptionServiceUnitTest {
    /*@MockBean
    private SubscriptionInfraRepository _subscriptionRespository;

    private SubscriptionApplicationService _subscriptionServicve;*/

  //  private FixtureConfiguration<Subscription> fixture;



    /*@TestConfiguration
    static class SubscriptionServiceUnitTestConfig{
        @Bean
        public SubscriptionApplicationService subscriptionService(){return new SubscriptionService}
    }*/



//    @BeforeEach()
//    public void setUp(){
//        //fixture = new AggregateTestFixture<Subscription>(Subscription.class);
//    }

    private FixtureConfiguration<Subscription> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(Subscription.class);
    }

    @Test
    public void createRegister() throws Exception{
        String subscriptionId = UUID.randomUUID().toString();
        Instant ocurredOn = Instant.now();
        RegisterSubscription registerSubscription = new RegisterSubscription(subscriptionId,"testt222","SWAGtest","75104901",ocurredOn);

        SubscriptionRegistered subscriptionRegistered = new SubscriptionRegistered(registerSubscription.getUserId(),registerSubscription.getFirstName(),registerSubscription.getLastName(),registerSubscription.getDni(),ocurredOn);

        fixture.given()
                .when(registerSubscription)
                .expectEvents(subscriptionRegistered);
    }



}
