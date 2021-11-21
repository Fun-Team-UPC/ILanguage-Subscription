package com.edu.upc.ilanguagesubscription.config;

import io.swagger.v3.core.model.ApiDescription;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
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
        Contact contact = new Contact();
        contact.setEmail("info@ilanguage.pe");
        contact.setName("Ilanguage");
        License license = new License();
        license.setName("Apache 2.0");
        license.setUrl("https://www.apache.org/licenses/LICENSE-2.0");


        contact.setUrl("https://las-bichotas2-0.github.io/LandingPage/");
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Subscription Aplication A P I")
                        .description("Subscription microservice for Ilanguage Application implemmented with Spring Boot RESTful service and docummented using springdoc-openapi-ui 3.0")
                        .contact(contact)
                        .termsOfService("htpp://swagger.io/terms/")
                        .license(license)

                );

    }
}
