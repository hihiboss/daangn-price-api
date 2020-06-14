package com.hihiboss.daangnpriceapi.application;

import com.hihiboss.daangnpriceapi.domain.SearchingHistory;
import com.hihiboss.daangnpriceapi.domain.SearchingHistoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SearchApplicationServiceTest {
    @Autowired
    private SearchApplicationService searchApplicationService;

    @Autowired
    private SearchingHistoryRepository searchingHistoryRepository;

    private SearchingHistory searchingHistory;

    @Before
    public void setupSearchingHistory() {
        searchingHistory = SearchingHistory.builder()
                .keyword("test keyword")
                .minPrice(1234)
                .maxPrice(5678)
                .articleIdList(new ArrayList<>())
                .build();

        searchingHistoryRepository.save(searchingHistory);
    }

    @Test
    public void whenGetAllSearchingHistories_thenReturnList() {
        // given

        // when
        List<SearchingHistory> searchingHistoryList = searchApplicationService.getAllSearchingHistories();

        // then
        assertThat(searchingHistoryList).isNotNull();
        assertThat(searchingHistoryList.size()).isEqualTo(1);
    }

    @Test
    public void givenKeyword_whenGetSearchingHistoriesByKeyword_thenReturnList() {
        // given
        String testKeyword = "test keyword";

        // when
        List<SearchingHistory> searchingHistoryList = searchApplicationService.getSearchingHistoriesByKeyword(testKeyword);

        // then
        assertThat(searchingHistoryList).isNotNull();
        assertThat(searchingHistoryList.size()).isEqualTo(1);
        assertThat(searchingHistoryList.get(0).getKeyword()).isEqualTo(testKeyword);
    }
}
