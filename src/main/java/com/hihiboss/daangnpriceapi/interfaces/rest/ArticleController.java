package com.hihiboss.daangnpriceapi.interfaces.rest;

import com.hihiboss.daangnpriceapi.application.SearchApplicationService;
import com.hihiboss.daangnpriceapi.domain.Article;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/articles")
@AllArgsConstructor
public class ArticleController {
    private SearchApplicationService searchApplicationService;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Article> getSearchResult(
            @RequestParam String keyword,
            @RequestParam int startPrice,
            @RequestParam int endPrice
    ) {
        return searchApplicationService.searchArticlesByPrice(keyword, startPrice, endPrice);
    }
}
