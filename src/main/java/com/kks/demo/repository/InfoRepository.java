package com.kks.demo.repository;

import com.kks.demo.domain.record.Records;
import com.kks.demo.dto.Follow;
import com.kks.demo.dto.MyRecord;
import com.kks.demo.dto.login.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 팔로워 리스트
     * */
    public List<Follow> getFollowerList(String userId){
        return this.jdbcTemplate.query("select f.followerIdx, u.userImg "
                + "from   Follow f "
                + "inner join User u "
                + "		on f.followerIdx = u.userId "
                + "where  f.followingIdx = " + userId,
                (rs, rowNum) -> new Follow(
                        rs.getString("followerIdx"),
                        rs.getString("userImg"))
        );
    }

    /**
     * 팔로잉 리스트
     * */
    public List<Follow> getFollowingList(String userId){
        return this.jdbcTemplate.query("select f.followingIdx, u.userImg "
                        + "from   Follow f "
                        + "inner join User u "
                        + "		on f.followingIdx = u.userId "
                        + "where  f.followerIdx = " + userId,
                (rs, rowNum) -> new Follow(
                        rs.getString("followingIdx"),
                        rs.getString("userImg"))
        );
    }

    /**
     * 좋아요 게시물 목록
     * */
    public List<MyRecord> getRecordLikeList(String userId){
        return this.jdbcTemplate.query("select rl.recordIdx, r.imgUrl "
                        + "from   RecordLike rl "
                        + "inner join Record r "
                        + "		on rl.recordIdx = r.recordIdx "
                        + "where  rl.userId = " + userId,
                (rs, rowNum) -> new MyRecord(
                        rs.getInt("recordIdx"),
                        rs.getString("imgUrl"))
        );
    }

    /**
     * 회원탈퇴
     * */
    // 5. 회원탈퇴 -> 팔로잉 리스트 삭제


}
