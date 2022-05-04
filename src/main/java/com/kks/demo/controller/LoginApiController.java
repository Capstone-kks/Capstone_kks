package com.kks.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kks.demo.dto.login.ImageUpdateDto;
import com.kks.demo.dto.login.JoinRequestDto;
import com.kks.demo.dto.login.NicknameUpdateDto;
import com.kks.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value="/login/updatename/userId={userId}", produces=MediaType.APPLICATION_JSON_VALUE)
    @JsonProperty("requestDto")
    public String updateName(@PathVariable String userId, @RequestBody NicknameUpdateDto requestDto){
        return loginService.update(userId, requestDto);
    }

    @PutMapping(value="/login/updateimage/userId={userId}", produces=MediaType.APPLICATION_JSON_VALUE)
    @JsonProperty("requestDto")
    public String updateImg(@PathVariable String userId, @RequestBody ImageUpdateDto requestDto){
        return loginService.update2(userId, requestDto);
    }


}
