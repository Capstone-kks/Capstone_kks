package com.kks.demo.repository;

import com.kks.demo.dto.Like;
import com.mysql.cj.protocol.ResultsetRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class LikeRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getRecordLikedCnt(int recordIdx){
        String status = "ACTIVE";
        try{
            return this.jdbcTemplate.queryForObject("select count(*) "
                            + "from RecordLike "
                            + "where recordIdx = " + recordIdx + " "
                            + "and status = 'ACTIVE'",
                    Integer.class
            );
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public String getRecordLikeActive(int recordIdx, String userId){
        try {
            return this.jdbcTemplate.queryForObject("select status "
                            + "from RecordLike "
                            + "where userId = " + userId + " AND recordIdx = " + recordIdx,
                    String.class);
        }catch (EmptyResultDataAccessException e){
            return "error";
        }
    }
}
