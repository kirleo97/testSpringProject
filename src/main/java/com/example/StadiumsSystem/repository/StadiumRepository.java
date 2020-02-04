package com.example.StadiumsSystem.repository;

import com.example.StadiumsSystem.domain.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StadiumRepository extends JpaRepository<Stadium, Integer> {
}