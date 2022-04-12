package com.kks.demo.dto.login;

import com.kks.demo.domain.login.LoginEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRequestDto {
    private String userid;
    private String nickname;
    private String userimg;


    @Builder
    public JoinRequestDto(String userid, String nickname, String userimg){
        this.userid = userid;
        this.nickname = nickname;
        this.userimg = userimg;
    }

    public LoginEntity toEntity(){
        return LoginEntity.builder()
                .userid(userid)
                .nickname(nickname)
                .userimg(userimg)
                .build();
    }
}
