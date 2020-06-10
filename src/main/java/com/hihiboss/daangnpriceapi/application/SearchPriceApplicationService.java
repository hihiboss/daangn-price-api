package com.hihiboss.daangnpriceapi.application;

import com.hihiboss.daangnpriceapi.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchPriceApplicationService {
    private CrawlService crawlService;
    private ParseService parseService;
    private SearchHistoryRepository searchHistoryRepository;

    @Transactional
    public List<Article> searchArticleWithPrice(String searchingKeyword, int startPrice, int endPrice) {
        if (!validatePriceScope(startPrice, endPrice)) {
            throw new IllegalArgumentException();
        }

        String crawledPage = crawlService.crawlPage(searchingKeyword);
        List<Article> articles = parseService.parseArticles(crawledPage);

        List<Article> searchedArticles = articles.stream()
                .filter(article -> article.isPriceContained(startPrice, endPrice))
                .collect(Collectors.toList());
        List<Long> searchedArticleIds = searchedArticles.stream()
                .map(Article::getId)
                .collect(Collectors.toList());

        searchHistoryRepository.save(
                SearchHistory.builder()
                        .keyword(searchingKeyword)
                        .minPrice(startPrice)
                        .maxPrice(endPrice)
                        .articleIdList(searchedArticleIds)
                        .build()
        );

        return searchedArticles;
    }

    private Boolean validatePriceScope(int startPrice, int endPrice) {
        return (startPrice >= 0) && (endPrice >= 0) && (startPrice < endPrice);
    }
}
