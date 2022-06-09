package com.kks.demo.repository;

import com.kks.demo.domain.record.Records;
import com.kks.demo.dto.Follow;
import com.kks.demo.dto.MyRecord;
import com.kks.demo.dto.login.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class InfoRepository {

    private JdbcTemplate jdbcTemplate;
    //public List<Records> findByUserId(String userId);

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * 내 정보
     * */
    public List<MyRecord> selectByUserId(String userId){
        return this.jdbcTemplate.query("select recordIdx, imgUrl "
                + "from Record "
                + "where userId = " + userId,
                (rs, rowNum) -> new MyRecord(
                        rs.getInt("recordIdx"),
                        rs.getString("imgUrl"))
        );
    }

    /**
     * 다른 사용자 게시물
     * */
    public List<MyRecord> selectByOtherUserId(String userId){
        return this.jdbcTemplate.query("select recordIdx, imgUrl "
                        + "from Record "
                        + "where userId = " + userId + " and postPublic = 1",
                (rs, rowNum) -> new MyRecord(
                        rs.getInt("recordIdx"),
                        rs.getString("imgUrl"))
        );
    }

    /**
     * 팔로워 리스트
     * */
    public List<Follow> getFollowerList(String userId){
        return this.jdbcTemplate.query("select f.followerIdx as userId, u.userImg, u.nickName "
                + "from   Follow f "
                + "inner join User u "
                + "		on f.followerIdx = u.userId "
                + "where  f.followingIdx = '" + userId + "'",
                (rs, rowNum) -> new Follow(
                        rs.getString("userId"),
                        rs.getString("userImg"),
                        rs.getString("nickName"))
        );
    }

    /**
     * 팔로잉 리스트
     * */
    public List<Follow> getFollowingList(String userId){
        return this.jdbcTemplate.query("select f.followingIdx as userId, u.userImg, u.nickName "
                        + "from   Follow f "
                        + "inner join User u "
                        + "		on f.followingIdx = u.userId "
                        + "where  f.followerIdx = '" + userId + "'",
                (rs, rowNum) -> new Follow(
                        rs.getString("userId"),
                        rs.getString("userImg"),
                        rs.getString("nickName"))
        );
    }

    /**
     * 팔로우 신청
     * */
    public int requestFollow(String followerIdx, String followingIdx){
        String insertQuery = "insert into Follow(followerIdx, followingIdx) values(?,?)";
        Object[] insertParams = new Object[]{followerIdx, followingIdx};
        this.jdbcTemplate.update(insertQuery, insertParams);
        return 1;
    }

    /**
     * 팔로우 취소
     * */
    public int cancelFollow(String followerIdx, String followingIdx){
        String deleteQuery = "delete from Follow where followerIdx = ? and followingIdx = ?";
        Object[] deleteParams = new Object[]{followerIdx, followingIdx};
        this.jdbcTemplate.update(deleteQuery, deleteParams);
        return 1;
    }

    /**
     * 팔로우 여부
     * */
    public int getFollowStatus(String userId, String followId){
        try{
            return this.jdbcTemplate.queryForObject("select count(*) "
                            + "from Follow "
                            + "where followerIdx = " + userId + " AND followingIdx = " + followId,
                    Integer.class);
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }


    /**
     * 좋아요 게시물 목록
     * */
    public List<MyRecord> getRecordLikeList(String userId){
        return this.jdbcTemplate.query("select rl.recordIdx, r.imgUrl "
                        + "from   RecordLike rl "
                        + "inner join Record r "
                        + "		on rl.recordIdx = r.recordIdx "
                        + "where  rl.userId = " + userId + " and status = 'ACTIVE'",
                (rs, rowNum) -> new MyRecord(
                        rs.getInt("recordIdx"),
                        rs.getString("imgUrl"))
        );
    }

    /**
     * 회원탈퇴
     * */
    //사용자 계정 삭제
    public int deleteUser(String userId){
        String deleteQuery = "delete from User where userId = '" + userId + "'";
        return this.jdbcTemplate.update(deleteQuery);
    }

    //팔로우 삭제
    public int deleteFollow(String userId){
        String deleteQuery = "delete from Follow where followerIdx = '" + userId
                + "' and followingIdx = '" + userId + "'";
        return this.jdbcTemplate.update(deleteQuery);
    }
    //게시글 삭제
    public int deleteRecord(String userId){
        String deleteQuery = "delete from Record where userId = '" + userId + "'";
        return this.jdbcTemplate.update(deleteQuery);
    }
    //좋아요 삭제
    public int deleteLike(String userId){
        String deleteQuery = "delete from RecordLike where userId = '" + userId + "'";
        return this.jdbcTemplate.update(deleteQuery);
    }
    //댓글 삭제
    public int deleteComment(String userId){
        String deleteQuery = "delete from Comment where userId = '" + userId + "'";
        return this.jdbcTemplate.update(deleteQuery);
    }
}
