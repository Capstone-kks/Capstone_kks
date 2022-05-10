package com.kks.demo.domain.record;

import com.kks.demo.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Records, Integer> {

    Long countByUserIdAndCategoryId(String userId, int categoryId);

}
