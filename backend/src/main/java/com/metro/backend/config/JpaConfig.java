package com.metro.backend.config;

import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;

@Configuration
public class JpaConfig {
    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        return new EntityManagerFactoryBuilder(
                vendorAdapter,
                dataSource -> new HashMap<>(),
                null
        );
    }
}
