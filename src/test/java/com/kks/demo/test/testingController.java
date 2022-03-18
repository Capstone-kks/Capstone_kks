package com.kks.demo.test;


import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testingController {
    @Autowired
    private testingRepository repo;

    @After
    public void cleanup(){
        repo.deleteAll();
    }

    @Test
    public void getTest(){
        String id = "id";
        String nickname = "nickname";

        repo.save(testingEntity.builder()
                .id(id)
                .nickname(nickname)
                .build());
    }

    List<testingEntity> listTest= repo.findAll();

    testingEntity entity = listTest.get(0);

    //assertThat(entity.getId()).isEqualTo(id);
    //assertThat(entity.getNickname()).isEqualTo(nickname);


    /*
    @GetMapping("/testing")
    public String listAll(Model model) {
        List<testingEntity> listTest= repo.findAll();
        model.addAllAttributes("listTest", listTest);
        return "testing";
    }
    */
}
