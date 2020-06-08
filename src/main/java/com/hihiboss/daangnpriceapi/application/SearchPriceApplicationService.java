package com.hihiboss.daangnpriceapi.application;

import com.hihiboss.daangnpriceapi.domain.Article;
import com.hihiboss.daangnpriceapi.domain.CrawlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SearchPriceApplicationService {
    private CrawlService crawlService;

    public List<Article> searchArticleWithPrice(String searchingKeyword, int startPrice, int endPrice) {
        return null;
    }
}
