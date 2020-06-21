package com.hihiboss.daangnpriceapi.interfaces.rest;

import com.hihiboss.daangnpriceapi.application.SearchApplicationService;
import com.hihiboss.daangnpriceapi.domain.SearchingHistory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/histories")
@AllArgsConstructor
public class HistoryController {
    private SearchApplicationService searchApplicationService;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<SearchingHistory> getAllHistories() {
        return searchApplicationService.getAllSearchingHistories();
    }

    @GetMapping("/{keyword}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<SearchingHistory> getHistoriesByKeyword(
            @PathVariable String keyword
    ) {
        return searchApplicationService.getSearchingHistoriesByKeyword(keyword);
    }
}
