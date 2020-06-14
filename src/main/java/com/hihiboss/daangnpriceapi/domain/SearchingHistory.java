package com.hihiboss.daangnpriceapi.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "searching_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "min_price")
    private int minPrice;

    @Column(name = "max_price")
    private int maxPrice;

    @Column(name = "article_id_list")
    @ElementCollection
    private List<Long> articleIdList = new ArrayList<>();

    @Builder
    public SearchingHistory(String keyword, int minPrice, int maxPrice, List<Long> articleIdList) {
        this.keyword = keyword;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.articleIdList = articleIdList;
    }
}
