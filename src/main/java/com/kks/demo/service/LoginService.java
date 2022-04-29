package com.kks.demo.service;


import com.kks.demo.domain.user.Users;
import com.kks.demo.dto.login.JoinRequestDto;
import com.kks.demo.domain.user.LoginRepository;
import com.kks.demo.dto.login.NicknameUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
