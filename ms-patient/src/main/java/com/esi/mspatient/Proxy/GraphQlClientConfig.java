package com.esi.mspatient.Proxy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQlClientConfig {


    @Bean
    @Qualifier("OrdonnanceGraphQlClient")
    HttpGraphQlClient OrdonnanceGraphQlClient() {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8082/graphql")
                .build();

        return HttpGraphQlClient.builder(webClient).build();
    }

    @Bean
    @Qualifier("RemboursementGraphQlClient")
    HttpGraphQlClient RemboursementGraphQlClient() {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8083/graphql")
                .build();

        return HttpGraphQlClient.builder(webClient).build();
    }


}