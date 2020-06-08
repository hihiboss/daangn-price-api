package com.hihiboss.daangnpriceapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Article {
    private Long id;
    private String title;
    private String content;
    private String region;
    private int price;

    public Boolean isPriceContained(int startPrice, int endPrice) {
        return (this.price >= startPrice) && (this.price <= endPrice);
    }
}
