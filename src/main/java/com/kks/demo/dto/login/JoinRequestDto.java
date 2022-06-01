package com.kks.demo.dto.login;

import com.kks.demo.domain.user.Users;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRequestDto {

    @ApiModelProperty(value = "ID", example = "steve", required = true)
    private String userId;

    @ApiModelProperty(value = "NAME", example = "steve", required = true)
    private String nickName;

    @ApiModelProperty(value = "IMAGE", example = "image link")
    private String userImg;


    @Builder
    public JoinRequestDto(String userId, String nickName, String userImg){
        this.userId = userId;
        this.nickName = nickName;
        this.userImg = userImg;
    }

    public Users toEntity(){
        return Users.builder()
                .userId(userId)
                .nickName(nickName)
                .userImg(userImg)
                .build();
    }
}
