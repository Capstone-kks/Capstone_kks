package com.kks.demo.test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="testing")
public class testingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testidx;

    private String id;
    private String nickname;

    @Builder
    public testingEntity(int testidx, String id, String nickname){
        this.testidx = testidx;
        this.id = id;
        this.nickname =nickname;
    }

}
