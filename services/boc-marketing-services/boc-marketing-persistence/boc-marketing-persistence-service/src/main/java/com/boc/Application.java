package com.boc;

import com.backbase.buildingblocks.backend.configuration.autoconfigure.BackbaseApplication;
import com.backbase.buildingblocks.jwt.internal.config.EnableInternalJwtConsumer;
import com.backbase.buildingblocks.registry.client.api.EnableRegistryClient;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@BackbaseApplication
@EnableInternalJwtConsumer
@EnableRegistryClient
@ComponentScan(basePackages = {"com.backbase", "com.boc"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)})
@EnableJpaRepositories(basePackages = {"com.backbase.buildingblocks.persistence.repository", "com.boc.persistence.repository"})
@EntityScan(basePackages = {"com.backbase.buildingblocks.persistence.model", "com.boc.persistence.model"})
public class Application extends SpringBootServletInitializer {

    public static void main(String... args) {
        build(new SpringApplicationBuilder()).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
        return build(springApplicationBuilder);
    }

    private static SpringApplicationBuilder build(SpringApplicationBuilder springApplicationBuilder) {
        return springApplicationBuilder.sources(Application.class);
    }
}
