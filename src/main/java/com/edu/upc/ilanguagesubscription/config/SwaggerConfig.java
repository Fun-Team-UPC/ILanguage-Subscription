package com.edu.upc.ilanguagesubscription.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableWebMvc
public class SwaggerConfig {
   /* @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(new Tag("Subcriptions", "Subcriptions"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }*/

    @Bean(name ="SubscriptionIlanguageOPEN-API")
    public OpenAPI iLanguageOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Subscription Aplication A P I")
                        .description("Subscription microservice for Ilanguage Application implemmented with Spring Boot RESTful service and docummented using springdoc-openapi-ui 3.0"));

    }
}
