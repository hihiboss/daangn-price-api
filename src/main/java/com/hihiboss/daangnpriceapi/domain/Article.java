package com.hihiboss.daangnpriceapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Article {
    private String title;
    private String content;
    private String region;
    private String price;
    private int priceValue;

    @Builder
    public Article(String title, String content, String region, String price) {
        this.title = title;
        this.content = content;
        this.region = region;
        this.price = price;
        this.priceValue = convertPriceToInteger(price);
    }

    public Boolean isPriceContained(int startPrice, int endPrice) {
        return (this.priceValue >= startPrice) && (this.priceValue <= endPrice);
    }

    private int convertPriceToInteger(String priceString) {
        if(priceString.equals("무료나눔")) {
            return 0;
        }

        if(priceString.equals("-")) {
            return -1;
        }

        String priceWithoutUnit = priceString.replace("원", "");
        String priceWithoutComma = priceWithoutUnit.replace(",", "");

        return Integer.parseInt(priceWithoutComma);
    }
}
