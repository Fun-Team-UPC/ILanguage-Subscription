package unitTest;


import com.edu.upc.ilanguagesubscription.command.application.services.SubscriptionApplicationService;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfra;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfraRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.meta.When;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SubscriptionServiceUnitTest {
    @MockBean
    private SubscriptionInfraRepository _subscriptionRespository;

    private SubscriptionApplicationService _subscriptionServicve;

    /*@TestConfiguration
    static class SubscriptionServiceUnitTestConfig{
        @Bean
        public SubscriptionApplicationService subscriptionService(){return new SubscriptionService}
    }*/

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
}
