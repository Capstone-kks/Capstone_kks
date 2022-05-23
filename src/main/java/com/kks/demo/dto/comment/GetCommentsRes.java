package com.kks.demo.dto.comment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCommentsRes {
    private int commentIdx; // 댓글 id
    private int recordIdx; // 레코드 id
    private String userId; // 댓글단 사용자
    private String nickName; // 댓글단 사용자 닉네임
    private String content; // 내용
    private String postDate;// 댓글단 시간
}
