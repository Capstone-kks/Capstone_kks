package com.kks.demo.dto.like;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLikeReq {
    private int recordIdx; // 좋아요를 누른 글
    private String userId; // 좋아요를 누른 사용자 id
}
