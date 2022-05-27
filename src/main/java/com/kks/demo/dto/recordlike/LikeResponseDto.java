package com.kks.demo.dto.recordlike;

import com.kks.demo.domain.record.Records;
import com.kks.demo.domain.recordlike.RecordLikes;
import lombok.Getter;

@Getter
public class LikeResponseDto {

    private int recordLikeIdx;

    private int recordIdx;
    private String userId;
    private String status;
    private String createdAt;

    public LikeResponseDto(RecordLikes entity){
        this.recordIdx = entity.getRecordIdx();
        this.userId = entity.getUserId();
        this.status = entity.getStatus();
        this.createdAt = entity.getCreatedAt();

    }
}
