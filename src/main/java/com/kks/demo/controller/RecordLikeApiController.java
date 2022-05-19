package com.kks.demo.controller;


import com.kks.demo.config.BaseException;
import com.kks.demo.config.BaseResponse;
import com.kks.demo.dto.like.PostLikeReq;
import com.kks.demo.service.RecordLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
