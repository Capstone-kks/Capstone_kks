package com.kks.demo.dto.record;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetDetailRecordRes {
    private int recordIdx;
    private String userId;
    private String title;
    private int categoryId;
    private float rate;
    private String content;
    private int postPublic;
    private String imgUrl;
    private String postDate;
    private int commentCount;
}
