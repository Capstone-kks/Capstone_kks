package com.kks.demo.domain.login;

import com.kks.demo.domain.login.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository <LoginEntity, Integer>{

    LoginEntity findByUserId(String userId);
}
