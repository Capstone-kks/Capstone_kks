package com.kks.demo.controller;


import com.kks.demo.config.BaseException;
import com.kks.demo.config.BaseResponse;
import com.kks.demo.dto.like.PostLikeReq;
import com.kks.demo.service.RecordLikeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/likes")
public class RecordLikeApiController {

    private final RecordLikeService recordLikeService;


    /**
     * 레코드(글)에 좋아요 하기
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<String> createRecordLike(@RequestBody PostLikeReq postLikeReq){

        try{
            String result = recordLikeService.createRecordLike(postLikeReq);
            return new BaseResponse<>(result);

        }catch (BaseException exception){
            exception.printStackTrace();
            return new BaseResponse<>(exception.getStatus());

        }
    }

    @ApiOperation(value="기록 좋아요 개수", notes="좋아요 개수 반환")
    @GetMapping(value="/countlike", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String CountLike (@RequestParam int recordIdx){
        //System.out.println("아이디:"+userId);
        return recordLikeService.CountLike(recordIdx);
    }
}
