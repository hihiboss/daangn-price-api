package com.hihiboss.daangnpriceapi.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleTests {
    private Article testArticle;

    @Before
    public void setupArticle() {
        testArticle = Article.builder()
                .id(1234L)
                .title("Test Title")
                .content("Test Content")
                .region("Test Region")
                .price("9999원")
                .build();
    }

    @Test
    public void givenPriceBetweenStartPriceAndEndPrice_whenIsPriceContained_thenReturnTrue() {
        // given
        int startPrice = 1000;
        int endPrice = 20000;

        // when
        Boolean testResult = testArticle.isPriceContained(startPrice, endPrice);

        // then
        assertThat(testResult).isTrue();
    }

    @Test
    public void givenPriceLowerThanStartPrice_whenIsPriceContained_thenReturnFalse() {
        // given
        int startPrice = 19999;
        int endPrice = 99999;

        // when
        Boolean testResult = testArticle.isPriceContained(startPrice, endPrice);

        // then
        assertThat(testResult).isFalse();
    }

    @Test
    public void givenPriceHigherThanEndPrice_whenIsPriceContained_thenReturnFalse() {
        // given
        int startPrice = 999;
        int endPrice = 1999;

        // when
        Boolean testResult = testArticle.isPriceContained(startPrice, endPrice);

        // then
        assertThat(testResult).isFalse();
    }
}
