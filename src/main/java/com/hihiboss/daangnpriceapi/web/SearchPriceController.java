package com.hihiboss.daangnpriceapi.web;

import com.hihiboss.daangnpriceapi.application.SearchPriceApplicationService;
import com.hihiboss.daangnpriceapi.domain.Article;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/article")
@AllArgsConstructor
public class SearchPriceController {
    private SearchPriceApplicationService searchPriceApplicationService;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Article> getResult(
            @RequestParam String keyword,
            @RequestParam int startPrice,
            @RequestParam int endPrice
    ) {
        return searchPriceApplicationService.searchArticleWithPrice(keyword, startPrice, endPrice);
    }
}
