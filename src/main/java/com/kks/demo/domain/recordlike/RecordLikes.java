package com.kks.demo.domain.recordlike;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="RecordLike")
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RecordLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //for auto increment
    private int recordLikeIdx;

    private int recordIdx;
    private String userId;
    private String status;
    private String createdAt;

    @Builder
    public RecordLikes(int recordIdx,
            String userId,
            String status,
            String createdAt) {

        this.recordIdx = recordIdx;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }

}
