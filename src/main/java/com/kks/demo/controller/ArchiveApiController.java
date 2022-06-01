package com.kks.demo.controller;

import com.kks.demo.dto.record.ResponseListbyCategoryDto;
import com.kks.demo.dto.record.SearchResponseDto;
import com.kks.demo.service.ArchiveService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/archive")
public class ArchiveApiController {

    private final ArchiveService archiveService;
    int port = 8080;

    //userId, category id 넘겨주면 해당되는 레코드의 id와 이미지 반환?
    //userId, category id 넘겨주면 해당 레코드리스트 반환?
    @ApiOperation(value="카테고리 별 recordId,Img리스트", notes="recordIdx, categoryId, imgUrl 반환")
    @GetMapping(value="/category", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<ResponseListbyCategoryDto> findbyCategory(@RequestParam String userId, @RequestParam int categoryId){
        List<ResponseListbyCategoryDto> searchList =archiveService.findbyCategory(userId, categoryId);
        //model.addAttribute(searchList);

        return searchList;
    }

    @ApiOperation(value="아카이브검색", notes="category가 같고, parameter keyword가 제목이나 내용에 들어있는 Record Entity들을 리스트로 반환")
    @GetMapping(value="/search", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<SearchResponseDto> SearchByCatKeyword(@RequestParam String userId, @RequestParam int categoryId, @RequestParam String keyword){
        List<SearchResponseDto> searchList = archiveService.SearchByCatKeyword(userId, categoryId, keyword);

        return searchList;
    }




}
