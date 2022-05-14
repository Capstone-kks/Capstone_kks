package com.kks.demo.service;


import com.kks.demo.domain.user.Users;
import com.kks.demo.dto.login.ImageUpdateDto;
import com.kks.demo.dto.login.JoinRequestDto;
import com.kks.demo.domain.user.LoginRepository;
import com.kks.demo.dto.login.NicknameUpdateDto;
import com.kks.demo.dto.login.UserResponseDto;
import com.kks.demo.dto.record.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Transactional
    public void save(JoinRequestDto requestDto){
        if ( loginRepository.findByUserId(requestDto.getUserId()) == null) {
            loginRepository.save(requestDto.toEntity());
        }
    }

    @Transactional
    public String update(String userId, NicknameUpdateDto reqeustDto){
        Users users = loginRepository.findByUserId(userId);
        users.update(reqeustDto.getNickName());

        return userId;
    }

    @Transactional
    public String update2(String userId, ImageUpdateDto reqeustDto){
        Users users = loginRepository.findByUserId(userId);
        users.update2(reqeustDto.getUserImg());

        return userId;
    }

    public UserResponseDto findByUserId (String userId) {
        Users users = loginRepository.findByUserId(userId);
        //.orElseThrow(()->new IllegalArgumentException("해당 Id 정보가 없음"))
        //UserResponseDto us = new UserResponseDto(users);
        //System.out.print(us);
        return new UserResponseDto(users);
        //return us;
    }
}
