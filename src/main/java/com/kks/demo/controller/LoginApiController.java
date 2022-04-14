package com.kks.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kks.demo.domain.login.LoginEntity;
import com.kks.demo.domain.login.LoginRepository;
import com.kks.demo.dto.login.JoinRequestDto;
import com.kks.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api")
public class LoginApiController {

    private final LoginService loginService;
    int port = 8080;

    HttpStatus resultStatus = HttpStatus.OK;   // 기본적으로 정상적으로 조회가 된다는 가정하에 반환하는 HTTP Status 값은 200 (OK) 입니다.

    private LoginRepository loginRepository;

    @RequestMapping(value="/login", produces=MediaType.APPLICATION_JSON_VALUE)
    @JsonProperty("requestDto")
    //@PostMapping(value="/login/{userId}/{nickName}/{userImg}", produces=MediaType.APPLICATION_JSON_VALUE)
    //@RequestMapping(value="/login/{userId}/{nickName}/{userImg}", method = {RequestMethod.GET, RequestMethod.POST}, produces=MediaType.APPLICATION_JSON_VALUE)
    public JoinRequestDto save(@RequestParam(value="userId") String userId,
                               @RequestParam(value="nickName") String nickName, @RequestParam(value="userImg", required = false) String userImg){
    //public JoinRequestDto save(@RequestBody JoinRequestDto requestDto){

        //give
        //String userId = "real";
        //String nickName = "real";
        //String userImg = "real";

        /*
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:" + port)
                .path("/api/login/{userId}/{nickName}/{userImg}")
                .queryParam("")
                .encode()
                .build()
                //.expand(userId, nickName, userImg)
                .toUri();
        //System.out.println(uri);


        String url = "http://localhost:" + port + "/api/login/" + userId + "/" + nickName + "/" + userImg;

        requestDto = JoinRequestDto.builder()
                .userId(userId)
                .nickName(nickName)
                .userImg(userImg)
                .build();



        String url = "http://localhost:" + port + "/api/login/" + userId + "/" + nickName + "/" + userImg;
        RestTemplate restTemplate = new RestTemplate();
        //when
        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(url, requestDto, Integer.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0);

        List<LoginEntity> all = loginRepository.findAll();
        assertThat(all.get(0).getUserId()).isEqualTo(userId);
        assertThat(all.get(0).getNickName()).isEqualTo(nickName);
        assertThat(all.get(0).getUserImg()).isEqualTo(userImg);
        */

        //return loginService.save(requestDto);
        return loginService.save(userId, nickName, userImg);
    }
}
