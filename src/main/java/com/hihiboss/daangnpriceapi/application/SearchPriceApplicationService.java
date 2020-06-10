package com.hihiboss.daangnpriceapi.application;

import com.hihiboss.daangnpriceapi.domain.Article;
import com.hihiboss.daangnpriceapi.domain.CrawlService;
import com.hihiboss.daangnpriceapi.domain.ParseService;
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
    private ParseService parseService;

    public List<Article> searchArticleWithPrice(String searchingKeyword, int startPrice, int endPrice) {
        if (!validatePriceScope(startPrice, endPrice)) {
            throw new IllegalArgumentException();
        }

        String crawledPage = crawlService.crawlPage(searchingKeyword);
        List<Article> articles = parseService.parseArticles(crawledPage);

        return articles.stream()
                .filter(article -> article.isPriceContained(startPrice, endPrice))
                .collect(Collectors.toList());
    }

    private Boolean validatePriceScope(int startPrice, int endPrice) {
        return (startPrice >= 0) && (endPrice >= 0) && (startPrice < endPrice);
    }
}
