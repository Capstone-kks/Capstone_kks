package com.kks.demo.service;

import com.kks.demo.config.BaseException;
import com.kks.demo.dao.comment.CommentDao;
import com.kks.demo.dto.comment.GetCommentsRes;
import com.kks.demo.dto.comment.PostCommentReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kks.demo.config.BaseResponseStatus.DATABASE_ERROR;


@RequiredArgsConstructor
@Service
public class CommentService {


    private final CommentDao commentDao;


    /**
     * 댓글 조회 API
     */
    public List<GetCommentsRes> getComments(int recordIdx) throws BaseException{
        try{
            List<GetCommentsRes> getCommentsRes= commentDao.getCommentList(recordIdx);
            return getCommentsRes;

        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 댓글 삭제
     */
    public String deleteComment(int commentIdx)throws BaseException{
        try{
            String result = commentDao.deleteComment(commentIdx);
            return result;
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }

    }




    /**
     * 댓글 작성
     */
    public String postComment(PostCommentReq postCommentReq)throws BaseException{
        try{
            String result = commentDao.postComment(postCommentReq);
            return result;

        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }







}
