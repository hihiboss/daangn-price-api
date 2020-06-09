package com.hihiboss.daangnpriceapi.infra;

import com.hihiboss.daangnpriceapi.config.DaangnConfig;
import com.hihiboss.daangnpriceapi.domain.CrawlService;
import com.hihiboss.daangnpriceapi.domain.Article;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JsoupCrawlService implements CrawlService {
    private DaangnConfig daangnConfig;

    @Override
    public List<Article> crawlArticles(String crawlingKeyword) {

        Elements elements = getElementsFromWeb(crawlingKeyword);
        if (elements == null){
            return null;
        }

        return elements.stream()
                .map(this::createArticleFromElement)
                .collect(Collectors.toList());
    }

    private Elements getElementsFromWeb(String keyword) {
        String daangnUrl = daangnConfig.url();

        try {
            Document doc = Jsoup.connect(daangnUrl + keyword).get();

            return doc.getElementsByClass("article-info");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Article createArticleFromElement(Element articleInfo) {
        Element articleTitleContent = articleInfo.getElementsByClass("article-title-content").get(0);

        return Article.builder()
                .title(articleTitleContent.getElementsByClass("article-title").text())
                .content(articleTitleContent.getElementsByClass("article-content").text())
                .region(articleInfo.getElementsByClass("article-region-name").text())
                .price(articleInfo.getElementsByClass("article-price").text())
                .build();
    }
}
