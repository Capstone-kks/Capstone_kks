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

    @PostMapping(value="/login", produces=MediaType.APPLICATION_JSON_VALUE)
    @JsonProperty("requestDto")
    public JoinRequestDto save(@RequestBody JoinRequestDto requestDto){
        loginService.save(requestDto);

        return requestDto;
    }

}
