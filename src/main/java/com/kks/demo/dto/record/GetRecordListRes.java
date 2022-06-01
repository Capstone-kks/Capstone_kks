package com.kks.demo.dto.record;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetRecordListRes {
    private String recordIdx;
    private String userId;
    private String title;
    private int categoryId;
    private float rate;
    private String content;
    private boolean postPublic;
    private String imgUrl;
    private String postDate;
    private int commentCount;
}
