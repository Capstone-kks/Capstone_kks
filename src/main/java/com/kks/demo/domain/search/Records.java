package com.kks.demo.domain.search;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kks.demo.dto.search.RecordDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name="Record")
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
//Entity
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordIdx;

    private String userId;

    private String title;

    private int categoryId;

    private int rate;

    private String content;

    private int postPublic;

    private String postDate;

    @Builder
    public Records(int recordId, String userId, String title, int categoryId, int rate, String content, int postPublic, String postDate){
        this.recordIdx = recordId;
        this.userId = userId;
        this.title = title;
        this.categoryId = categoryId;
        this.rate = rate;
        this.content = content;
        this.postPublic = postPublic;
        this.postDate = postDate;
    }

}
