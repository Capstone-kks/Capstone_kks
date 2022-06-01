package com.kks.demo.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="User")
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //for auto increment
    private int userIndex;

    //@JsonProperty("userId")
    private String userId;

    //@JsonProperty("nickName")
    private String nickName;

    //@JsonProperty("userImg")
    private String userImg;

    @Builder
    public Users(String userId, String nickName, String userImg){
        this.userId = userId;
        this.nickName = nickName;
        this.userImg = userImg;
    }

    public void update(String nickName){
        this.nickName = nickName;
    }

    public void update2(String userImg){
        this.userImg = userImg;
    }
}
