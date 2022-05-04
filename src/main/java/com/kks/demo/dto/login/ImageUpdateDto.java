package com.kks.demo.dto.login;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageUpdateDto {
    private String userImg;

    @Builder
    public ImageUpdateDto(String userImg){
        this.userImg = userImg;
    }
}
