package com.kks.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kks.demo.config.BaseException;
import com.kks.demo.config.BaseResponse;
import com.kks.demo.dto.record.GetDetailRecordRes;
import com.kks.demo.dto.record.RecordSaveDto;
import com.kks.demo.dto.record.SearchResponseDto;
import com.kks.demo.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
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

    @ApiOperation(value="문화패턴 - 전체기간", notes="String으로 모든 카테고리의 개수 순서대로 반환.\n카테고리 순서 : 공연 도서 드라마 연/뮤 영화 음악 전시 기타")
    @GetMapping(value="/countall", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //public long CountAllbyCat (@RequestParam String userId, @RequestParam int categoryId){
    public String CountAllbyCat (@RequestParam String userId){
        //System.out.println("아이디:"+userId);
        return recordService.CountAllbyCat(userId);
    }

    @ApiOperation(value="문화패턴 - 이번달", notes="String으로 모든 카테고리의 개수 순서대로 반환.\n'YYYY-MM'형식의 String으로 이번달 값을 parameter로 줘야함")
    @GetMapping(value="/countmonth", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //public long CountAllbyCat (@RequestParam String userId, @RequestParam int categoryId){
    public String CountMonthbyCat (@RequestParam String userId, @RequestParam String postDate){
        //System.out.println("아이디:"+userId);
        return recordService.CountMonthbyCat(userId, postDate);
    }

//    @ApiOperation(value="기록 작성", notes="기록 작성")
//    @PostMapping(value="/save", produces=MediaType.APPLICATION_JSON_VALUE)
//    @JsonProperty("requestDto")
//    public RecordSaveDto save(@RequestBody RecordSaveDto requestDto){
//        recordService.save(requestDto);
//
//        return requestDto;
//    }

    @ApiOperation(value="기록 작성", notes="기록 작성")
    @ResponseBody
    @PostMapping(value="/save")
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<String> save(@RequestBody RecordSaveDto requestDto){
        try{
            String result = recordService.postRecord(requestDto);
            return new BaseResponse<>(result);
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }


    }

    @ApiOperation(value="검색", notes="parameter keyword가 제목이나 내용에 들어있는 Record Entity들을 리스트로 반환")
    @GetMapping(value="/search/keyword", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<SearchResponseDto> findByTitleAndContent(@RequestParam String keyword){
        //public String findByTitleAndContent(@RequestParam String keyword, Model model){
        System.out.println("키워드:"+keyword);
        List<SearchResponseDto> searchList = recordService.SearchByKeyword(keyword);
        //model.addAttribute(searchList);

        return searchList;
    }

    @ApiOperation(value="기록 보기", notes="recordIdx와 userId로 한 개 단위로 기록 불러오기")
    @GetMapping(value="/search/recordId")
    @ResponseBody
    public SearchResponseDto SearchByUserRecord(@RequestParam int recordIdx, @RequestParam String userId){
        return recordService.SearchByUserRecord(recordIdx, userId);
    }


    /**
     * 글 (세부내용)조회 API
     */
    @ResponseBody
    @GetMapping(value="/{recordIdx}")
    public BaseResponse<List<GetDetailRecordRes>> getDetailRecord(@PathVariable("recordIdx") int recordIdx){
        try{
            List<GetDetailRecordRes> getDetailRecordRes = recordService.getDetailRecord(recordIdx);
            return new BaseResponse<>(getDetailRecordRes);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 피드 글 목록 API
     */
    @GetMapping(value="/all?")
    public BaseResponse<List<GetFeedRecordRes>>



}