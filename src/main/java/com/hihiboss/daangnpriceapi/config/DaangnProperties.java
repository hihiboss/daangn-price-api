package com.hihiboss.daangnpriceapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "daangn")
public class DaangnProperties {
    private String url;
    private int maxPage;
    private int timeout;
}
