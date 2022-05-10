package com.kks.demo.controller;

import com.kks.demo.dto.login.UserResponseDto;
import com.kks.demo.service.LoginService;
import com.kks.demo.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/record")
public class RecordApiController {

    private final RecordService recordService;
    int port = 8080;

    @GetMapping(value="/countall", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //public long CountAllbyCat (@RequestParam String userId, @RequestParam int categoryId){
    public String CountAllbyCat (@RequestParam String userId){
        //System.out.println("아이디:"+userId);
        return recordService.CountAllbyCat(userId);
    }
}
