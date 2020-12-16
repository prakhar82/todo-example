package com.boc.auth;

import com.boc.api.spec.AccessTokenApi;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public AccessTokenApi getAccessTokenApi() {
        return new AccessTokenApi();
    }
}
