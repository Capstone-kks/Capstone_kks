package com.kks.demo.dao.comment;

import com.kks.demo.dto.comment.GetCommentsRes;
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




}
