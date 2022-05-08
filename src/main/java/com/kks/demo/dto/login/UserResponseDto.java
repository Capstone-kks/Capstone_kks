package com.kks.demo.dto.login;

import com.kks.demo.domain.user.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserResponseDto {

    private int userIndex;
    private String userId;
    private String nickName;
    private String userImg;

    public UserResponseDto(Users entity){
        this.userIndex = entity.getUserIndex();
        this.userId = entity.getUserId();
        this.nickName = entity.getNickName();
        this.userImg = entity.getUserImg();
    }

}
