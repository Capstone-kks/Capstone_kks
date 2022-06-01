package com.kks.demo.dto.comment;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCommentReq {
    private int recordIdx; // 레코드 id
    private String userId; // 댓글단 사용자
    private String content; // 내용



}
