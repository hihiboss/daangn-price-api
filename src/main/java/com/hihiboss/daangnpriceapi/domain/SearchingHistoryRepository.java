package com.hihiboss.daangnpriceapi.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchingHistoryRepository extends JpaRepository<SearchingHistory, Long> {
    public List<SearchingHistory> findByKeyword(String keyword);
}
