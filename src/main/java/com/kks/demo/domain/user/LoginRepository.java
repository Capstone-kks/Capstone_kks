package com.kks.demo.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginRepository extends JpaRepository <Users, Integer>{

    Users findByUserId(String userId);

    //List<Users> findByUserId(String userId);

}
