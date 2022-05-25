package com.kks.demo.domain.record;

import com.kks.demo.domain.user.Users;
import com.kks.demo.dto.MyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface RecordRepository extends JpaRepository<Records, Integer> {

    Long countByUserIdAndCategoryId(String userId, int categoryId);
    Long countByUserIdAndCategoryIdAndPostDateStartsWith(String userId, int categoryId, String postDate);

    //List<Records> findByTitleOrContentContains(String keyword, String keyword2);
    List<Records> findByTitleContainsOrContentContains(String keyword, String keyword2);

    //for archive search
    List<Records> findByUserIdAndCategoryIdAndTitleContainsOrContentContains(String userId, int categoryId, String keyword, String keyword2);

    Records findByRecordIdxAndUserId(int recordIdx, String userId);

    List<Records> findByUserIdAndCategoryId(String userId, int categoryId);


}
