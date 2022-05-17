package com.kks.demo.dao.record;

import com.kks.demo.dto.like.PostLikeReq;
import com.kks.demo.dto.like.PostLikeRes;
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


    /**
     * 글 좋아요 관련
     */

    // 해당 질문이 존재하는지 여부
    public int likeRecordIdxExist(int getRecordIdx){
        String checkLikeRecordQuery="SELECT EXISTS(SELECT recordIdx from Record where recordIdx=?)";
        return this.jdbcTemplate.queryForObject(checkLikeRecordQuery,int.class,getRecordIdx);
    }

    // 좋아요 상태
    public int getLikeStatus(PostLikeReq postLikeReq){
        String getLikeStatusQuery = "SELECT\n" +
                "        distinct CASE\n" +
                "            WHEN EXISTS(SELECT userId, recordIdx from RecordLike where userId = ? AND recordIdx = ? AND status = 'ACTIVE')= '1' then 2\n" +
                "            WHEN EXISTS(SELECT userId, recordIdx from RecordLike where userId = ? AND recordIdx = ? AND status = 'INACTIVE')= '1' then 3\n" +
                "            ELSE 1 END\n" +
                "FROM RecordLike ql\n" +
                "right join Record q on ql.recordIdx = q.recordIdx";

        Object[] getLikeStatParams = new Object[]{postLikeReq.getUserId(),postLikeReq.getRecordIdx(),
                postLikeReq.getUserId(),postLikeReq.getRecordIdx()};
        return this.jdbcTemplate.queryForObject(getLikeStatusQuery,int.class,getLikeStatParams);
    }

    public String createRecordLike(PostLikeReq postLikeReq, int status){
        Object[] createRecordLikeParams=new Object[]{postLikeReq.getRecordIdx(),postLikeReq.getUserId()};
        String result;
        switch (status){
            case 1:// 최초 좋아요
                String createRecordLikeQuery = "INSERT INTO RecordLike(recordIdx,userId) VALUES(?,?)";
                this.jdbcTemplate.update(createRecordLikeQuery,createRecordLikeParams);
                result=new String("해당 글을 좋아요 했습니다.");
                return result;
            case 2: // 좋아요 취소
                String changeToInActiveQuery= "UPDATE RecordLike\n"+
                        "SET status='INACTIVE'\n"+
                        "WHERE recordIdx=? AND userId=?";
                this.jdbcTemplate.update(changeToInActiveQuery,createRecordLikeParams);
                return new String("해당 글을 좋아요 취소했습니다.");
            default: // 3
                String changeToActiveQuery="UPDATE RecordLike\n"+
                        "SET status='ACTIVE'\n"+
                        "WHERE recordIdx=? AND userId=?";
                this.jdbcTemplate.update(changeToActiveQuery,createRecordLikeParams);
                return "해당 글을 다시 좋아요 했습니다.";

        }
    }

}
