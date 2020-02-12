package com.example.StadiumsSystem.repository;

import com.example.StadiumsSystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}