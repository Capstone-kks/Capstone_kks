package com.kks.demo.dao.record;


import com.kks.demo.dto.Recommend;
import com.kks.demo.dto.calendar.GetCalendarRes;

import com.kks.demo.domain.record.SearchResponse;

import com.kks.demo.dto.like.PostLikeReq;
import com.kks.demo.dto.record.GetDetailRecordRes;
import com.kks.demo.dto.record.GetFeedRecordRes;
import com.kks.demo.dto.record.ModifyRecordReq;
import com.kks.demo.dto.record.RecordSaveReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RecordDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * 글 작성 API
     */
    public int postRecord(String imgUrl, RecordSaveReq requestDto){


        String InsertQueQuery = "INSERT INTO Record(userId,title,\n" +
                "                     categoryId,rate,content,postPublic,imgUrl,postDate,commentCount)\n" +
                "                     VALUES (?,?,?,?,?,?,?,?,?)";
        Object[] InsertQueParams = new Object[]{requestDto.getUserId(), requestDto.getTitle(),
               requestDto.getCategoryId(),requestDto.getRate(),requestDto.getContent(),requestDto.getPostPublic(),
        imgUrl,requestDto.getPostDate(),requestDto.getCommentCount()};
        this.jdbcTemplate.update(InsertQueQuery, InsertQueParams);

        String lastInsertIdQuery="select last_insert_id()";

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    /**
     * 글 (세부내용) 조회 API
     */
    public GetDetailRecordRes getDetailRecord(int recordIdx,String userId){
        String getRecordQuery= "SELECT r.recordIdx, r.userId,r.title,r.categoryId,r.rate,r.content,r.postPublic,r.imgUrl,r.postDate,r.commentCount,\n" +
                "IFNULL(rl.isLiked,'N') AS isLiked, writerName,profileImg\n"+
                "FROM Record r \n"+
                "LEFT JOIN (SELECT recordIdx, CASE status WHEN 'ACTIVE' THEN 'Y' WHEN 'INACTIVE' THEN 'N' END AS isLiked FROM RecordLike WHERE userId=?) rl\n"+
                "ON r.recordIdx = rl.recordIdx\n"+
                "LEFT JOIN (SELECT userId,nickName as writerName , userImg as profileImg FROM User) u on u.userId=r.userId\n"+
                "WHERE r.recordIdx=?";
        Object[] getRecordParams = new Object[]{userId,recordIdx};
        return this.jdbcTemplate.queryForObject(getRecordQuery,
                (rs,rowNum)-> new GetDetailRecordRes(
                        rs.getInt("recordIdx"),
                        rs.getString("userId"),
                        rs.getString("title"),
                        rs.getInt("categoryId"),
                        rs.getFloat("rate"),
                        rs.getString("content"),
                        rs.getInt("postPublic"),
                        rs.getString("imgUrl"),
                        rs.getString("postDate"),
                        rs.getInt("commentCount"),
                        rs.getString("isLiked"),
                        rs.getString("writerName"),
                        rs.getString("profileImg")),
                getRecordParams);

    }


    /**
     * 글 수정 API
     */

    public String updateRecord(String userId, int recordIdx, ModifyRecordReq modifyRecordReq, String imgUrl){

        String modifyRecordQuery = "UPDATE Record set title=?, categoryId=?, rate=?, content=?,postPublic=?,imgUrl=? where recordIdx=?";
        Object[] modifyRecordParams=new Object[]{modifyRecordReq.getTitle(),modifyRecordReq.getCategoryId(),modifyRecordReq.getRate(),
        modifyRecordReq.getContent(),modifyRecordReq.getPostPublic(),imgUrl,recordIdx};
        this.jdbcTemplate.update(modifyRecordQuery,modifyRecordParams);
        return new String("글 내용을 변경했습니다.");

    }
    public String updateRecordExImg(String userId, int recordIdx, ModifyRecordReq modifyRecordReq){

        String modifyRecordQuery = "UPDATE Record set title=?, categoryId=?, rate=?, content=?,postPublic=? where recordIdx=?";
        Object[] modifyRecordParams=new Object[]{modifyRecordReq.getTitle(),modifyRecordReq.getCategoryId(),modifyRecordReq.getRate(),
                modifyRecordReq.getContent(),modifyRecordReq.getPostPublic(),recordIdx};
        this.jdbcTemplate.update(modifyRecordQuery,modifyRecordParams);
        return new String("글 내용을 변경했습니다.");
    }


    /**
     * 글 삭제 API
     */
    public String deleteRecord(String userId, int recordIdx ){
        String deleteRecordQuery = "DELETE FROM Record WHERE recordIdx=?";
        int deleteRecordParam = recordIdx;
        this.jdbcTemplate.update(deleteRecordQuery,deleteRecordParam);
        return new String("해당 글을 삭제했습니다.");

    }


    /**
     * 사용자 인증(확인) 처리
     */
    //todo 나중에 시간나면...





    /**
     * 글 좋아요 API 관련
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

    // 반복 좋아요 처리
    public String createRecordLike(PostLikeReq postLikeReq, int status){
        Object[] createRecordLikeParams=new Object[]{postLikeReq.getRecordIdx(),postLikeReq.getUserId()};
        String result;
        switch (status){
            case 1:// 최초 좋아요
                String createRecordLikeQuery = "INSERT INTO RecordLike(recordIdx,userId) VALUES(?,?)";
                this.jdbcTemplate.update(createRecordLikeQuery,createRecordLikeParams);
                result=new String("해당 글에 좋아요를 눌렀습니다.");
                return result;
            case 2: // 좋아요 취소
                String changeToInActiveQuery= "UPDATE RecordLike\n"+
                        "SET status='INACTIVE'\n"+
                        "WHERE recordIdx=? AND userId=?";
                this.jdbcTemplate.update(changeToInActiveQuery,createRecordLikeParams);
                return new String("해당 글에 좋아요를 취소했습니다.");
            default: // 3 (다시 좋아요)
                String changeToActiveQuery="UPDATE RecordLike\n"+
                        "SET status='ACTIVE'\n"+
                        "WHERE recordIdx=? AND userId=?";
                this.jdbcTemplate.update(changeToActiveQuery,createRecordLikeParams);
                return "해당 글에 다시 좋아요를 눌렀습니다.";

        }
    }


    /**
     * 피드 목록 조회 API
     */

    public List<GetFeedRecordRes> getFeedListByCondition(String loginUserId,int sort, boolean isFollowCheck){
        String getFeedListQuery=null;
        String getFeedListParams=loginUserId;
        // todo

        if(isFollowCheck==true){// 내가 팔로워를 한 사람의 게시글만 볼때
            if(sort==1){ // 최신순
                getFeedListQuery="SELECT r.recordIdx,r.userId,u.nickName,r.title,r.content,r.postDate,r.imgUrl\n"+
                        "FROM Record r\n"+
                        "LEFT JOIN (SELECT userId,nickName FROM User) u on u.userId= r.userId\n"+
                        "LEFT JOIN (SELECT followerIdx,followingIdx as MyFollow FROM Follow) f on f.followerIdx= ? \n"+
                        "WHERE r.postPublic=1 AND r.userId=MyFollow\n"+
                        "ORDER BY postDate DESC";


            }else if(sort==2){// 인기순
                getFeedListQuery="SELECT r.recordIdx,r.userId,u.nickName,r.title,r.content,r.postDate,r.imgUrl\n"+
                        "FROM Record r\n"+
                        "LEFT JOIN (SELECT recordIdx,COUNT(*) as likeCount\n"+
                        "FROM RecordLike WHERE status='ACTIVE'\n"+
                        "GROUP BY recordIdx) rl on rl.recordIdx = r.recordIdx\n"+
                        "LEFT JOIN (SELECT userId,nickName FROM User) u on u.userId=r.userId\n"+
                        "LEFT JOIN (SELECT followerIdx,followingIdx as MyFollow FROM Follow) f on f.followerIdx=?\n"+
                        "WHERE postPublic=1 AND r.userId=MyFollow\n"+
                        "ORDER BY rl.likeCount DESC";


            }
            return this.jdbcTemplate.query(getFeedListQuery,
                    (rs,rowNum)-> new GetFeedRecordRes(
                            rs.getInt("recordIdx"),
                            rs.getString("userId"),
                            rs.getString("nickName"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("postDate"),
                            rs.getString("imgUrl")),
                    getFeedListParams);

        }else{ // 모든 사람의 게시물을 볼때 // ifFollowCheck = false
            if(sort==1){ // 최신순
                getFeedListQuery="SELECT r.recordIdx,r.userId,u.nickName,r.title,r.content,r.postDate,r.imgUrl\n"+
                        "FROM Record r\n"+
                        "LEFT JOIN (SELECT userId,nickName FROM User) u on u.userId=r.userId\n"+
                        "WHERE r.postPublic=1\n"+
                        "ORDER BY postDate DESC";


            }else if(sort==2){// 인기순
                getFeedListQuery="SELECT r.recordIdx,r.userId,u.nickName,r.title,r.content,r.postDate,r.imgUrl\n"+
                        "FROM Record r\n"+
                        "LEFT JOIN (SELECT recordIdx,COUNT(*) as likeCount\n"+
                        "FROM RecordLike WHERE status='ACTIVE'\n"+
                        "GROUP BY recordIdx) rl on rl.recordIdx = r.recordIdx\n"+
                        "LEFT JOIN (SELECT userId,nickName FROM User) u on u.userId=r.userId\n"+
                        "WHERE postPublic=1\n"+
                        "ORDER BY rl.likeCount DESC";


            }

            return this.jdbcTemplate.query(getFeedListQuery,
                    (rs,rowNum)-> new GetFeedRecordRes(
                            rs.getInt("recordIdx"),
                            rs.getString("userId"),
                            rs.getString("nickName"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("postDate"),
                            rs.getString("imgUrl"))
            );
        }


    }

    public List<GetCalendarRes> getCalendarData(String userId, String yearMonth) {
        String getCalendarListQuery = "SELECT recordIdx,imgUrl,postDate\n" +
                "FROM Record\n" +
                "WHERE userId=? AND postDate LIKE ?";

        String yearMonth_ = yearMonth + "%";

        Object[] getCalendarListParams = new Object[]{userId, yearMonth_};

        return this.jdbcTemplate.query(getCalendarListQuery,
                (rs, rowNum) -> new GetCalendarRes(
                        rs.getInt("recordIdx"),
                        rs.getString("imgUrl"),
                        rs.getString("postDate")),
                getCalendarListParams);
    }


    /**
     * 검색 결과 정렬
     * */
    public List<SearchResponse> getSearchListByCondition(String keyword, String loginUserId, int sort) {
        String getFeedListQuery = null;
        String getFeedListParams = loginUserId;

        if(sort==1){ // 최신순
            getFeedListQuery="SELECT r.recordIdx,r.userId,u.nickName,r.title,r.rate,r.content,r.postDate,r.imgUrl\n"+
                    "FROM Record r\n"+
                    "LEFT JOIN (SELECT userId,nickName FROM User) u on u.userId=r.userId\n"+
                    "WHERE postPublic=1 AND (content LIKE '%"+ keyword +
                    "%' OR title LIKE '%"+ keyword +"%')\n"+
                    "ORDER BY postDate DESC";


        }else if(sort==2) {// 인기순
            getFeedListQuery = "SELECT r.recordIdx,r.userId,u.nickName,r.title,r.rate,r.content,r.postDate,r.imgUrl\n"+
                    "FROM Record r\n"+
                      "LEFT JOIN (SELECT recordIdx,COUNT(*) as likeCount\n"+
                    "FROM RecordLike WHERE status='ACTIVE'\n"+
                    "GROUP BY recordIdx) rl on rl.recordIdx = r.recordIdx\n"+
                    "LEFT JOIN (SELECT userId,nickName FROM User) u on u.userId=r.userId\n"+
                    "WHERE postPublic=1 AND (content LIKE '%"+ keyword +
                    "%' OR title LIKE '%"+ keyword +"%')\n"+
                    "ORDER BY rl.likeCount DESC";

        }

        return this.jdbcTemplate.query(getFeedListQuery,
                (rs,rowNum)-> new SearchResponse(
                        rs.getInt("recordIdx"),
                        rs.getString("userId"),
                        rs.getString("nickName"),
                        rs.getString("title"),
                        rs.getFloat("rate"),
                        rs.getString("content"),
                        rs.getString("postDate"),
                        rs.getString("imgUrl"))
        );

    }

    /**
     * 사용자 추천
     * */
    public List<Recommend> getRecommendRecord(int categoryId){
        return this.jdbcTemplate.query("select recordIdx, title, rate, content, imgUrl, postDate " +
                "from Record " +
                "where categoryId = " + categoryId + " and rate >= 3.0 and postPublic=1 " +
                "order by rand() limit 5",
                (rs, rowNum) -> new Recommend(
                        rs.getInt("recordIdx"),
                        rs.getString("title"),
                        rs.getFloat("rate"),
                        rs.getString("content"),
                        rs.getString("imgUrl"),
                        rs.getString("postDate"))
        );
    }

}
