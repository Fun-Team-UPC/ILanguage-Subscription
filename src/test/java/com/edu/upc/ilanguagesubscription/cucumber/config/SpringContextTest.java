package com.edu.upc.ilanguagesubscription.cucumber.config;

import com.edu.upc.ilanguagesubscription.IlanguageSubscriptionApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IlanguageSubscriptionApplication.class)
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
