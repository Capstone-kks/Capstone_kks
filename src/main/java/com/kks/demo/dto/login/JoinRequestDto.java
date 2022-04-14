package com.kks.demo.dto.login;

import com.kks.demo.domain.login.LoginEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRequestDto {
    private String userId;
    private String nickName;
    private String userImg;


    @Builder
    public JoinRequestDto(String userId, String nickName, String userImg){
        this.userId = userId;
        this.nickName = nickName;
        this.userImg = userImg;
    }

    public LoginEntity toEntity(){
        return LoginEntity.builder()
                .userId(userId)
                .nickName(nickName)
                .userImg(userImg)
                .build();
    }
}
