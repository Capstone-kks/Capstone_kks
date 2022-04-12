package com.kks.demo.service;


import com.kks.demo.dto.login.JoinRequestDto;
import com.kks.demo.domain.login.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final LoginRepository loginRepository;

    @Transactional
    public int save(JoinRequestDto requestDto){
        return loginRepository.save(requestDto.toEntity()).getUserindex();
    }
}
