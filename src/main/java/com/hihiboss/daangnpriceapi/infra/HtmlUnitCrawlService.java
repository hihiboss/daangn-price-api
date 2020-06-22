package com.hihiboss.daangnpriceapi.infra;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hihiboss.daangnpriceapi.config.DaangnConfig;
import com.hihiboss.daangnpriceapi.domain.CrawlService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HtmlUnitCrawlService implements CrawlService {
    private DaangnConfig daangnConfig;
    private WebClient webClient;

    @Override
    public List<String> crawlPages(String crawlingKeyword) {
        String url = daangnConfig.url();

        try {
            HtmlPage page = webClient.getPage(url + crawlingKeyword);

            HtmlElement loadButton = getLoadButton(page);
            int totalPage = getTotalPages(loadButton);
            int configuredMaxPage = daangnConfig.maxPage();

            if (totalPage > configuredMaxPage) {
                totalPage = configuredMaxPage;
            }

            List<String> htmlStringList = new ArrayList<>();
            for (int pageNum = 1; pageNum < totalPage; pageNum++) {
                htmlStringList.add(getHtmlPageString(page, pageNum));
            }
            return htmlStringList;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getHtmlPageString(HtmlPage page, int pageNumber) throws IOException {
        HtmlElement loadButton = getLoadButton(page);
        loadButton.setAttribute("data-page", String.valueOf(pageNumber));

        HtmlPage resultPage = loadButton.click();
        return resultPage.asXml();
    }

    private HtmlElement getLoadButton(HtmlPage page) {
        return page.getFirstByXPath("//div[@class='more-btn']");
    }

    private int getTotalPages(HtmlElement button) {
        return Integer.parseInt(button.getAttribute("data-total-pages"));
    }

    private int getCurrentPage(HtmlElement button) {
        return Integer.parseInt(button.getAttribute("data-page"));
    }
}
