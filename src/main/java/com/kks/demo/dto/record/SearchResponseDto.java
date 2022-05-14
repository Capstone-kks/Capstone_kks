package com.kks.demo.dto.record;

import com.kks.demo.domain.record.Records;
import lombok.Getter;

@Getter
public class SearchResponseDto {

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

    public SearchResponseDto(Records entity){
        this.recordIdx = entity.getRecordIdx();
        this.userId = entity.getUserId();
        this.title = entity.getTitle();
        this.categoryId = entity.getCategoryId();
        this.rate = entity.getRate();
        this.content = entity.getContent();
        this.postPublic = entity.getPostPublic();
        this.imgUrl = entity.getImgUrl();
        this.postDate = entity.getPostDate();
        this.commentCount = entity.getCommentCount();
    }
}
