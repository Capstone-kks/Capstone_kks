package com.kks.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kks.demo.dto.login.JoinRequestDto;
import com.kks.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RequiredArgsConstructor
@RestController
public class LoginApiController {

    private final LoginService loginService;

    //@PostMapping(value="/api/login")
    @RequestMapping(value="/api/login", method = {RequestMethod.GET, RequestMethod.POST})
    @JsonProperty("requestDto")
    public int save(@RequestBody JoinRequestDto requestDto){
        return loginService.save(requestDto);
    }
}
