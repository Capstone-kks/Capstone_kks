package com.kks.demo.domain.record;

import com.kks.demo.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Records, Integer> {

    Long countByUserIdAndCategoryId(String userId, int categoryId);

    List<Records> findByTitleOrContentContains(String keyword, String keyword2);

}
