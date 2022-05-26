package com.kks.demo.repository;

import com.kks.demo.dto.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class LikeRepository {

    private JdbcTemplate jdbcTemplate;
    //public List<Records> findByUserId(String userId);

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Like> getRecordLiked(int recordIdx, String userId){
        return this.jdbcTemplate.query("select status, (select count(*) "
                + "from RecordLike "
                + "where recordIdx = " + recordIdx + ") as 'likecnt' "
                + "from RecordLike "
                + "where userId = " + userId,
                (rs,rowNum) -> new Like(
                        rs.getString("status"),
                        rs.getInt("likeCnt"))
        );
    }
}
