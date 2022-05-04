package com.kks.demo.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="User")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //for auto increment
    private int userIndex;

    private String userId;
    private String nickName;
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
