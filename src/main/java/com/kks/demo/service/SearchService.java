package com.kks.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.kks.demo.domain.search.Records;
import com.kks.demo.domain.search.SearchRepository;
import com.kks.demo.dto.search.RecordDto;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchRepository searchRepository;

    @Transactional
    public List<Records> search(String keyword){
        return  searchRepository.findAllSearch(keyword);
    }
}
