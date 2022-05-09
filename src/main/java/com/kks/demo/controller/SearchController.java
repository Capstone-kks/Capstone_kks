package com.kks.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.kks.demo.dto.search.RecordDto;
import com.kks.demo.dto.search.SearchMapper;
import com.kks.demo.service.SearchService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/search")
public class SearchController {

    private final SearchService searchService;

    /*
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<RecordDto> selectAllRecords() throws Exception{
        List<RecordDto> recordList = new ArrayList<RecordDto>();

        try {
            recordList = searchService.selectAllRecords();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recordList;
    }
    */

    @ResponseBody
    @GetMapping
    public ResponseEntity<List<RecordDto>> searchResult(@PathVariable String keyword){
        List<RecordDto> entity = searchService.search(keyword)
                .stream()
                .map(SearchMapper::toResponseDto)
                .collect(Collectors.toList());
        try {
            return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(entity, HttpStatus.BAD_REQUEST);
        }
    }
}
