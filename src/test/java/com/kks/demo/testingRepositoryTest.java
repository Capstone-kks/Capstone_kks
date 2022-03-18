package com.kks.demo;


import com.kks.demo.test.testingEntity;
import com.kks.demo.test.testingRepository;
import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class testingRepositoryTest{

    @Autowired
    testingRepository testingRepository;

    /*
    @After
    public void cleanup(){
        testingRepository.deleteAll();
    }
    */

    @Test
    public void getsave(){
        //given
        String id = "123";
        String nickname ="123";

        testingRepository.save(testingEntity.builder()
                .id(id)
                .nickname(nickname)
                .build());


        //when
        List<testingEntity> testlist = testingRepository.findAll();

        //then
        testingEntity entity = testlist.get(0);
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getNickname()).isEqualTo(nickname);

    }


}
