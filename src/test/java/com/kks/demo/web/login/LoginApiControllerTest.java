package com.kks.demo.web.login;


import com.kks.demo.domain.login.LoginEntity;
import com.kks.demo.domain.login.LoginRepository;
import com.kks.demo.dto.login.JoinRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@WebAppConfiguration
@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginApiControllerTest {

    //@LocalServerPort
    private int port = 8080;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LoginRepository loginRepository;

    /*
    @After
    public void tearDown() throws Exception{
        loginRepository.deleteAll();
    }
    */

    @Test
    public void Join() throws Exception {
        //give
        String userid = "123";
        String nickname = "123";
        String userimg = "123";

        JoinRequestDto requestDto = JoinRequestDto.builder()
                .userid(userid)
                .nickname(nickname)
                .userimg(userimg)
                .build();

        String url = "http://localhost:" + port + "/api/login";

        //when
        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(url, requestDto, Integer.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0);

        List<LoginEntity> all = loginRepository.findAll();
        assertThat(all.get(0).getUserid()).isEqualTo(userid);
        assertThat(all.get(0).getNickname()).isEqualTo(nickname);
        assertThat(all.get(0).getUserimg()).isEqualTo(userimg);

    }
}
