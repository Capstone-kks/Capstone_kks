package com.kks.demo.dao.comment;

import com.kks.demo.dto.comment.GetCommentsRes;
import com.kks.demo.dto.comment.PostCommentReq;
import com.kks.demo.dto.like.PostLikeReq;
import com.kks.demo.dto.record.GetDetailRecordRes;
import com.kks.demo.dto.record.GetFeedRecordRes;
import com.kks.demo.dto.record.ModifyRecordReq;
import com.kks.demo.dto.record.RecordSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CommentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetCommentsRes> getCommentList(int recordIdx){
        String getCommentsQuery="SELECT commentIdx,recordIdx,c.userId,u.nickName,content,postDate\n"+
                "FROM Comment c\n"+
                "LEFT JOIN (SELECT userId,nickName FROM User) u on c.userId=u.userId\n"+
                "WHERE recordIdx=?";
        int getCommentParam = recordIdx;
        return this.jdbcTemplate.query(getCommentsQuery,
                (rs,rowNum)->new GetCommentsRes(
                        rs.getInt("commentIdx"),
                        rs.getInt("recordIdx"),
                        rs.getString("userId"),
                        rs.getString("nickName"),
                        rs.getString("content"),
                        rs.getString("postDate")
                ),getCommentParam);
    }

    /**
     * 댓글 삭제
     */
    public String deleteComment(int commentIdx){
        String deleteCommentQuery = "DELETE FROM Comment WHERE commentIdx=?";
        int deleteCommentParam = commentIdx;
        this.jdbcTemplate.update(deleteCommentQuery,deleteCommentParam);
        return new String("해당 댓글을 삭제했습니다.");
    }

    /**
     * 댓글 작성
     */
    public String postComment(PostCommentReq postCommentReq){
        String postCommentQuery = "INSERT INTO Comment(recordIdx,userId,content)\n"+
                "VALUES (?,?,?)";
        Object[] postCommentParams=new Object[]{postCommentReq.getRecordIdx(),postCommentReq.getUserId(),postCommentReq.getContent()};
        this.jdbcTemplate.update(postCommentQuery,postCommentParams);
        return new String ("댓글을 작성했습니다.");
    }




}
