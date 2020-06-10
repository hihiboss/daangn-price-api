package com.hihiboss.daangnpriceapi.infra;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hihiboss.daangnpriceapi.config.DaangnConfig;
import com.hihiboss.daangnpriceapi.domain.CrawlService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class HtmlUnitCrawlService implements CrawlService {
    private DaangnConfig daangnConfig;
    private WebClient webClient;

    @Override
    public String crawlPage(String crawlingKeyword) {
        String url = daangnConfig.url();

        try {
            HtmlPage page = webClient.getPage(url + crawlingKeyword);

            HtmlElement loadButton = getLoadButton(page);
            int totalPage = getTotalPages(loadButton);
            int currentPage = getCurrentPage(loadButton);

            while(currentPage < daangnConfig.maxPage() && currentPage < totalPage) {
                page = loadButton.click();
                webClient.waitForBackgroundJavaScript(daangnConfig.timeout());

                loadButton = getLoadButton(page);
                totalPage = getTotalPages(loadButton);
                currentPage = getCurrentPage(loadButton);
            }

            return page.asXml();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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
