package com.hihiboss.daangnpriceapi.domain;

import java.util.List;

public interface ParseService {
    public List<Article> parseArticles(String html);
}
