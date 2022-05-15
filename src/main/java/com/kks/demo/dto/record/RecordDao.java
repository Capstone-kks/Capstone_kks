package com.kks.demo.dto.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
@Repository
public class RecordDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // 글 작성

    public String postRecord(RecordSaveDto requestDto){


        String InsertQueQuery = "INSERT INTO Record(userId,title,\n" +
                "                     categoryId,rate,content,postPublic,imgUrl,postDate,commentCount)\n" +
                "                     VALUES (?,?,?,?,?,?,?,?,?)";
        Object[] InsertQueParams = new Object[]{requestDto.getUserId(), requestDto.getTitle(),
               requestDto.getCategoryId(),requestDto.getRate(),requestDto.getContent(),requestDto.getPostPublic(),
        requestDto.getImgUrl(),requestDto.getPostDate(),requestDto.getCommentCount()};
        this.jdbcTemplate.update(InsertQueQuery, InsertQueParams);

        return new String("게시글을 작성했습니다.");
    }
}
