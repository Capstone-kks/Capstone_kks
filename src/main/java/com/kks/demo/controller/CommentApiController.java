package com.kks.demo.controller;

import com.kks.demo.config.BaseException;
import com.kks.demo.config.BaseResponse;
import com.kks.demo.dto.comment.GetCommentsRes;
import com.kks.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/comment")
public class CommentApiController {
    private final CommentService commentService;


    /**
     * 댓글 조회 API
     * [Get] /api/comment/:recordIdx
     */
    @GetMapping("/{recordIdx}")
    public BaseResponse<List<GetCommentsRes>> getComments(@PathVariable("recordIdx") int recordIdx){
        try{
            List<GetCommentsRes> getCommentsRes = commentService.getComments(recordIdx);
            return new BaseResponse<>(getCommentsRes);

        }catch (BaseException e){
            return new BaseResponse<>((e.getStatus()));
        }
    }

}
