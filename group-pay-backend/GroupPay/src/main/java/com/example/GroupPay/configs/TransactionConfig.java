package com.example.GroupPay.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TransactionConfig {

    @Value("${merchant.base-url}")
    private String merchantBaseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(merchantBaseUrl).build();
    }
}
