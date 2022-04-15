package com.kks.demo.service;


import com.kks.demo.domain.login.LoginEntity;
import com.kks.demo.dto.login.JoinRequestDto;
import com.kks.demo.domain.login.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

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
}
