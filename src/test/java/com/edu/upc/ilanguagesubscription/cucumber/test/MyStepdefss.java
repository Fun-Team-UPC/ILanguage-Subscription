package com.edu.upc.ilanguagesubscription.cucumber.test;

import com.edu.upc.ilanguagesubscription.command.application.handlers.SubscriptionEventHandler;
import com.edu.upc.ilanguagesubscription.command.domain.Subscription;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfraRepository;
import com.edu.upc.ilanguagesubscription.cucumber.config.SpringIntegrationTest;
import com.google.type.DateTime;
import contracts.commands.RegisterSubscription;
import contracts.events.SubscriptionRegistered;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
//import static org.hamcrest.MatcherAssert.assertThat;
import java.io.IOException;
import java.time.Instant;

import static org.hamcrest.Matchers.is;
import static org.assertj.core.api.Assertions.assertThat;

public class MyStepdefss  {
    private FixtureConfiguration<Subscription> fixture;




    @Given("the client is on the main page")
    public void theClientIsOnTheMainPage() {



    }

    @When("the client searches for a subscription {int}")
    public void theClientSearchesForASubscription(int arg0) throws IOException {
        //executeGet();
       /* fixture.given(new SubscriptionRegistered("sdsadsdsad","Fully", 5,84, Instant.now()))
                .when(new RegisterSubscription("sdsadsdsad", "Fully", 5,84, Instant.now()))
                .expectSuccessfulHandlerExecution();*/

        fixture = new AggregateTestFixture<>(Subscription.class);
        fixture.given()
                .when(new RegisterSubscription("sdsadsdsddsdrwa", "ffdsfdsf", 14,99,Instant.now()))
                .expectEvents(new SubscriptionRegistered("sdsadsdsddsdrwa", "ffdsfdsf", 14,99,Instant.now()));
    }

    @Then("the client receives subscription {int} details")
    public void theClientReceivesSubscriptionDetails(int arg0) throws IOException {
        // final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        // assertThat(currentStatusCode.value()).isEqualTo(200);
    }
}


