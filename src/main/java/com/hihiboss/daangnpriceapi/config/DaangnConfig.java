package com.hihiboss.daangnpriceapi.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({DaangnProperties.class})
public class DaangnConfig {

    @Bean
    public DaangnProperties daangnProperties() {
        return new DaangnProperties();
    }

    @Bean
    public String url() {
        return daangnProperties().getUrl();
    }

    @Bean
    public int maxPage() { return daangnProperties().getMaxPage(); }

    @Bean
    public int timeout() { return daangnProperties().getTimeout(); }
}
