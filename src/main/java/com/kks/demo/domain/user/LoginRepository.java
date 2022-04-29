package com.kks.demo.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository <Users, Integer>{

    Users findByUserId(String userId);
}
