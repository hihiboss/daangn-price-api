package com.hihiboss.daangnpriceapi.application;

import com.hihiboss.daangnpriceapi.domain.Article;
import com.hihiboss.daangnpriceapi.domain.CrawlService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SearchPriceApplicationService {
    private CrawlService crawlService;

    public List<Article> searchArticleWithPrice(String searchingKeyword, int startPrice, int endPrice) {
        if (!validatePriceScope(startPrice, endPrice)) {
            throw new IllegalArgumentException();
        }

        List<Article> crawledArticles = crawlService.crawlArticles(searchingKeyword);
        return crawledArticles.stream()
                .filter(article -> article.isPriceContained(startPrice, endPrice))
                .collect(Collectors.toList());
    }

    private Boolean validatePriceScope(int startPrice, int endPrice) {
        return (startPrice >= 0) && (endPrice >= 0) && (startPrice > endPrice);
    }
}
