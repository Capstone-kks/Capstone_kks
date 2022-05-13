package com.kks.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kks.demo.dto.record.RecordSaveDto;
import com.kks.demo.dto.record.SearchResponseDto;
import com.kks.demo.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/record")
public class RecordApiController {

    private final RecordService recordService;
    int port = 8080;

    @GetMapping(value="/countall", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //public long CountAllbyCat (@RequestParam String userId, @RequestParam int categoryId){
    public String CountAllbyCat (@RequestParam String userId){
        //System.out.println("아이디:"+userId);
        return recordService.CountAllbyCat(userId);
    }

    @GetMapping(value="/countmonth", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //public long CountAllbyCat (@RequestParam String userId, @RequestParam int categoryId){
    public String CountMonthbyCat (@RequestParam String userId, @RequestParam String postDate){
        //System.out.println("아이디:"+userId);
        return recordService.CountMonthbyCat(userId, postDate);
    }

    @PostMapping(value="/save", produces=MediaType.APPLICATION_JSON_VALUE)
    @JsonProperty("requestDto")
    public RecordSaveDto save(@RequestBody RecordSaveDto requestDto){
        recordService.save(requestDto);

        return requestDto;
    }

    @GetMapping(value="/search", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<SearchResponseDto> findByTitleAndContent(@RequestParam String keyword){
    //public String findByTitleAndContent(@RequestParam String keyword, Model model){
        System.out.println("키워드:"+keyword);
        List<SearchResponseDto> searchList = recordService.SearchByKeyword(keyword);
        //model.addAttribute(searchList);

        return searchList;
    }
}
