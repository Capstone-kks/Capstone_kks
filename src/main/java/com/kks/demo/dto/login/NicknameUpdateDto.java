package com.kks.demo.dto.login;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Getter
@NoArgsConstructor
public class NicknameUpdateDto {
    private String nickName;

    @Builder
    public NicknameUpdateDto(String nickName){
        this.nickName = nickName;
    }
}
