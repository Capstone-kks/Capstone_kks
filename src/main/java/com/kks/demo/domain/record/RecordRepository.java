package com.kks.demo.domain.record;

import com.kks.demo.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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
