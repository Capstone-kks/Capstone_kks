package com.kks.demo.controller;


import com.kks.demo.config.BaseException;
import com.kks.demo.config.BaseResponse;
import com.kks.demo.dto.calendar.GetCalendarRes;
import com.kks.demo.dto.like.PostLikeReq;
import com.kks.demo.service.RecordLikeService;
import com.kks.demo.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/calendar")
public class CalendarApiController {

    private final RecordService recordService;


    /**
     * 달력에 대한 정보 조회 API
     */
    @ResponseBody
    @GetMapping("/{userId}/{yearMonth}")
    public BaseResponse<List<GetCalendarRes>> createRecordLike(@PathVariable("userId") String userId, @PathVariable("yearMonth") String yearMonth){

        try{
            List<GetCalendarRes> getCalendarRes = recordService.getCalendarData(userId,yearMonth);
            return new BaseResponse<>(getCalendarRes);

        }catch (BaseException exception){
            exception.printStackTrace();
            return new BaseResponse<>(exception.getStatus());

        }
    }
}
