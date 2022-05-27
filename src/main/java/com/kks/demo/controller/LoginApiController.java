package com.kks.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kks.demo.config.BaseException;
import com.kks.demo.dto.login.ImageUpdateDto;
import com.kks.demo.dto.login.JoinRequestDto;
import com.kks.demo.dto.login.NicknameUpdateDto;
import com.kks.demo.dto.login.UserResponseDto;
import com.kks.demo.service.LoginService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api")
public class LoginApiController {

    private final LoginService loginService;
    int port = 8080;

    HttpStatus resultStatus = HttpStatus.OK;   // 기본적으로 정상적으로 조회가 된다는 가정하에 반환하는 HTTP Status 값은 200 (OK) 입니다.

    @ApiOperation(value="회원 등록", notes="회원 등록")
    @PostMapping(value="/login", produces=MediaType.APPLICATION_JSON_VALUE)
    @JsonProperty("requestDto")
    public JoinRequestDto save(@RequestBody JoinRequestDto requestDto){
        loginService.save(requestDto);

        return requestDto;
    }

    @ApiOperation(value="회원정보 - 닉네임 수정", notes="닉네임 수정.")
    @PutMapping(value="/login/updatename/userId={userId}", produces=MediaType.APPLICATION_JSON_VALUE)
    @JsonProperty("requestDto")
    public String updateName(@PathVariable String userId, @RequestBody NicknameUpdateDto requestDto){
        return loginService.update(userId, requestDto);
    }

    @ApiOperation(value="회원정보 - 카카오프사로 수정", notes="카카오프사로 수정.")
    @PutMapping(value="/login/updateimage/userId={userId}", produces=MediaType.APPLICATION_JSON_VALUE)
    @JsonProperty("requestDto")
    public String updateImg(@PathVariable String userId, @RequestBody ImageUpdateDto requestDto){
        return loginService.update2(userId, requestDto);
    }

    @ApiOperation(value="회원정보 가져오기", notes="개인 단위로 회원 정보 가져오기")
    //@GetMapping(value="/login/get/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value="/login/get", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //public UserResponseDto findByUserId (@PathVariable String userId){
    public UserResponseDto findByUserId (@RequestParam String userId){
        System.out.println("아이디:"+userId);
        return loginService.findByUserId(userId);
    }

    @GetMapping(value="/test", produces=MediaType.APPLICATION_JSON_VALUE)
    public String test(@RequestParam String testing){
        return testing;
    }


    //Editing Profile Image using Multipart
    @ApiOperation(value="회원정보 - 프사 수정", notes="프사 수정.")
    @PutMapping(value="/login/updateimagefile/userId={userId}", produces=MediaType.APPLICATION_JSON_VALUE)
    @JsonProperty("requestDto")
    public String updateImgfile(@PathVariable String userId, @RequestPart(value="images") MultipartFile multipartFile, @RequestPart("ProfImg") ImageUpdateDto requestDto) {
        try {
            if (multipartFile != null) {
                String imageUrl = loginService.uploadS3Image(multipartFile, userId);
                requestDto = new ImageUpdateDto(imageUrl);
                return loginService.update2(userId, requestDto);
            }
            else return loginService.update2(userId, requestDto);
        } catch (BaseException e) {
            e.printStackTrace();
            return loginService.update2(userId, requestDto);
        }
    }

}
