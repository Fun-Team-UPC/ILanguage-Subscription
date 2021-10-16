package unitTest;


import com.edu.upc.ilanguagesubscription.command.application.services.SubscriptionApplicationService;
import com.edu.upc.ilanguagesubscription.command.infra.SubscriptionInfraRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SubscriptionServiceUnitTest {
    @MockBean
    private SubscriptionInfraRepository _subscriptionRespository;

    @Autowired
    private SubscriptionApplicationService _subscriptionServicve;

}
