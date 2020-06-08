package com.hihiboss.daangnpriceapi.infra;

import com.hihiboss.daangnpriceapi.domain.CrawlService;
import com.hihiboss.daangnpriceapi.domain.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsoupCrawlService implements CrawlService {
    @Override
    public List<Article> crawlArticles(String crawlingKeyword) {
        return null;
    }
}
