package com.edu.upc.ilanguagesubscription.unitTest;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.junit.jupiter.SpringExtension;


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



    @BeforeEach()
    public void setUp(){
        //fixture = new AggregateTestFixture<Subscription>(Subscription.class);
    }



}
