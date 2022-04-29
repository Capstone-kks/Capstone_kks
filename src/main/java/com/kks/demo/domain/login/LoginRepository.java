package com.kks.demo.domain.login;

import com.kks.demo.domain.login.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository <Users, Integer>{

    Users findByUserId(String userId);
}
