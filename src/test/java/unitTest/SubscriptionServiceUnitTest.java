package unitTest;


import com.edu.upc.ilanguagesubscription.command.application.dto.response.RegisterSubscriptionRes;
import com.edu.upc.ilanguagesubscription.command.application.services.SubscriptionApplicationService;
import com.edu.upc.ilanguagesubscription.command.domain.Subscription;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfra;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfraRepository;
import contracts.commands.RegisterSubscription;
import contracts.events.SubscriptionRegistered;
import io.axoniq.axonserver.grpc.query.SubscriptionQueryResponse;
import org.aspectj.lang.annotation.Before;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Time;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;


@ExtendWith(SpringExtension.class)
public class SubscriptionServiceUnitTest {
    @MockBean
    private SubscriptionInfraRepository _subscriptionRespository;

    private SubscriptionApplicationService _subscriptionServicve;

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




    @Test
    @DisplayName("Get subscription by name with valid name then return true")
    public void whenGetSubscriptionByNameWithValidNameThenReturnsSubscription(){
        //Arrange
        String name = "FullPass";
        SubscriptionInfra subscription  = new SubscriptionInfra();
        subscription.setName(name);
        subscription.setId("HDUD5FSDFDSFGJ");
        when(_subscriptionRespository.findByName(name)).thenReturn(Optional.of(subscription));
        Optional<SubscriptionInfra> foundSubscription = _subscriptionRespository.findByName(name);
        assertThat(foundSubscription.get().getName()).isEqualTo(subscription.getName());
    }


    @Test
    @DisplayName("Get subscription by name with valid name then return true")
    public void ssd(){
        //https://docs.axoniq.io/reference-guide/v/3.3/part-ii-domain-logic/testing
        /*fixture.given()
                .when(new RegisterSubscription("sddsadsadsad","sadasd",5,100))
                .expectSuccessfulHandlerExecution()
                .expectReturnValue(new RegisterSubscriptionRes("sddsadsadsad","sadasd",5,100));
               // .expectNoEvents(new SubscriptionRegistered("sddsadsadsad","sadasd", 5,100, Instant.now()));*/
        apply(new SubscriptionRegistered("sddsadsadsad","sadasd",5,100, Instant.now()));
        assertEquals(1, AggregateLifecycle.isLive());

    }


   /* @Test
    @DisplayName("Get subscription by name with valid name then return true")
    public void ssdd(){
       Subscription subscription = new Subscription();
       subscription.handle(new RegisterSubscription("sddsadsadsad","sadasd",5,100));
       assertThat(5).isEqualTo(subscription.getMonthDuration());
    }*/

}
