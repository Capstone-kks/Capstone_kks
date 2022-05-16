package com.kks.demo.dao.record;

import com.kks.demo.dto.record.GetDetailRecordRes;
import com.kks.demo.dto.record.GetRecordListRes;
import com.kks.demo.dto.record.RecordSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RecordDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // 글 작성 API

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

    // 글 (세부내용) 조회 API
    public List<GetDetailRecordRes> getDetailRecord(int recordIdx){
        String getRecordQuery= "SELECT * FROM Record WHERE recordIdx=?";
        int getRecordParam = recordIdx;
        return this.jdbcTemplate.query(getRecordQuery,
                (rs,rowNum)-> new GetDetailRecordRes(
                        rs.getInt("recordIdx"),
                        rs.getString("userId"),
                        rs.getString("title"),
                        rs.getInt("categoryId"),
                        rs.getInt("rate"),
                        rs.getString("content"),
                        rs.getBoolean("postPublic"),
                        rs.getString("imgUrl"),
                        rs.getString("postDate"),
                        rs.getInt("commentCount")),
                getRecordParam);

    }








    // 글 수정



    // 피드 목록 불러옴.
//    public List<GetRecordListRes> getRecordsByOption(int sort, String userId){
//        String getQuestionsQuery;
//        String orderBy = "";
//        // todo
//        // 생각해보니 팔로우 한 사람만 보려면 우선 그걸 체크했는지 여부를 boolean값으로 받은 후에
    // 조건 추가 ** 공개여부가 1인 유저는
//        // 지금 로그인한 유저 아이디 값도 같이 받고
//        // 그게 true이면 로그인한 유저가 팔로우한 사람들이 글만 볼 수 있도록 하고
//        // false이면 그냥 최신순 인기순만 고려
//    }
}
