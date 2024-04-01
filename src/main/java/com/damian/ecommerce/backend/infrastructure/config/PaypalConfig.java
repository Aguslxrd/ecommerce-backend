package com.damian.ecommerce.backend.infrastructure.config;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaypalConfig {
    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String secretClientKey;

    @Value("${paypal.mode}")
    private String paypalMode;

    @Bean
    public APIContext apiContext(){
        return new APIContext(clientId, secretClientKey, paypalMode);
    }
}
