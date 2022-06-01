package com.kks.demo.dto.record;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetFeedRecordRes { // 피드 조회 응답값
    private int recordIdx; // 레코드 id
    private String userId; // 유저 id
    private String nickName; // 유저 닉네임
    private String title; // 글 제목
    private String content;  // 글 내용
    private String postDate; // 작성 날자
    private String imgUrl; // 글 사진
}
