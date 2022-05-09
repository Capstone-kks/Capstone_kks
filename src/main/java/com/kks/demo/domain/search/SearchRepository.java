package com.kks.demo.domain.search;

import com.kks.demo.dto.search.RecordDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Records, String> {
        List<Records> findAllSearch(String keyword);
}
