package com.kks.demo.domain.login;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="User")
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //for auto increment
    private int userindex;

    private String userid;
    private String nickname;
    private String userimg;

    @Builder
    public LoginEntity(String userid, String nickname, String userimg){
        this.userid = userid;
        this.nickname = nickname;
        this.userimg = userimg;
    }
}
