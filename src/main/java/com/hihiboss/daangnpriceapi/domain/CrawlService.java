package com.hihiboss.daangnpriceapi.domain;

import java.util.List;

public interface CrawlService {
    public List<String> crawlPages(String crawlingKeyword);
}
