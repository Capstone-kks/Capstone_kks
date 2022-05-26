package com.kks.demo.domain.recordlike;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordLikeRespository extends JpaRepository<RecordLikes, Integer> {

    //좋아요 개수
    Long countByRecordIdxAndStatus(int recordIdx, String status);

    //좋아요 여부 (STATUS) 확인 위해 컬럼 찾기
    RecordLikes findByRecordIdxAndUserId(int recordIdx, String userId);
}
