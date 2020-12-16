package com.boc.integration;

import com.boc.api.spec.MarketingObjectApi;
import com.boc.api.spec.UpdateMarketingObjectApi;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public MarketingObjectApi getAccessTokenApi() {
        return new MarketingObjectApi();
    }

    @Bean
    public UpdateMarketingObjectApi getUpdateMarketingObjectApi() {
        return new UpdateMarketingObjectApi();
    }
}
