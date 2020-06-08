package com.hihiboss.daangnpriceapi.domain;

import com.hihiboss.daangnpriceapi.domain.Article;

import java.util.List;

public interface CrawlService {
    public List<Article> crawlArticles(String crawlingKeyword);
}
