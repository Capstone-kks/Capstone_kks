package com.kks.demo.controller;

import com.kks.demo.config.BaseException;
import com.kks.demo.config.BaseResponse;
import com.kks.demo.dto.comment.GetCommentsRes;
import com.kks.demo.dto.comment.PostCommentReq;
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



    /**
     * 댓글 작성 API
     */

    @PostMapping("/creation")
    public BaseResponse<String> postComment(@RequestBody PostCommentReq postCommentReq){
        try{
            String result = commentService.postComment(postCommentReq);
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }



    /**
     * 댓글 삭제 API
     */
    @DeleteMapping("/{commentIdx}")
    public BaseResponse<String> deleteRecord(@PathVariable("commentIdx") int commentIdx){
        try{
            String result = commentService.deleteComment(commentIdx);
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
