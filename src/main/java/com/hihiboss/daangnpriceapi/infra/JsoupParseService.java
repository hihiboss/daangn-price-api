package com.hihiboss.daangnpriceapi.infra;

import com.hihiboss.daangnpriceapi.domain.Article;
import com.hihiboss.daangnpriceapi.domain.ParseService;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JsoupParseService implements ParseService {

    @Override
    public List<Article> parseArticles(String html) {

        Elements elements = getElementsFromString(html);

        return elements.stream()
                .map(this::createArticleFromElement)
                .collect(Collectors.toList());
    }

    private Elements getElementsFromString(String htmlString) {
        Document doc = Jsoup.parse(htmlString);
        return doc.getElementsByClass("article-info");
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