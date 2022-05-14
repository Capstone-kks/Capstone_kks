package com.kks.demo.domain.record;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Record")
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //for auto increment
    private int recordIdx;

    private String userId;
    private String title;

    private int categoryId;
    private int rate;
    private String content;
    private int postPublic;

    private String imgUrl;
    private String postDate;

    private int commentCount;

    @Builder
    public Records(String userId,  String title, int categoryId,
                  int rate, String content, int postPublic,
                  String imgUrl, String postDate, int commentCount){
        this.userId = userId;
        this.title = title;
        this.categoryId = categoryId;
        this.rate = rate;
        this.content = content;
        this.postPublic = postPublic;
        this.imgUrl = imgUrl;
        this.postDate = postDate;
        this.commentCount = commentCount;
    }



}
