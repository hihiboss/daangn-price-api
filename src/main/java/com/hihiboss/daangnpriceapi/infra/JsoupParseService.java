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
        Document doc = Jsoup.parse(html);

        Elements elements = getFleaMarketArticleElements(doc);

        return elements.stream()
                .map(this::createArticleFromElement)
                .collect(Collectors.toList());
    }

    private Elements getFleaMarketArticleElements(Document doc) {
        return doc.getElementsByClass("flea-market-article-link");
    }

    private Article createArticleFromElement(Element fleaMarketArticle) {
        String articleHref = fleaMarketArticle.attr("href");
        String articleIdStr = articleHref.substring("/articles/".length());
        Long articleId = Long.parseLong(articleIdStr);

        Element articleInfo = fleaMarketArticle.getElementsByClass("article-info").first();
        Element articleTitleContent = articleInfo.getElementsByClass("article-title-content").first();

        return Article.builder()
                .id(articleId)
                .title(articleTitleContent.getElementsByClass("article-title").text())
                .content(articleTitleContent.getElementsByClass("article-content").text())
                .region(articleInfo.getElementsByClass("article-region-name").text())
                .price(articleInfo.getElementsByClass("article-price").text())
                .build();
    }
}