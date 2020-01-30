package com.example.StadiumsSystem.repository;

import com.example.StadiumsSystem.domain.EventType;
import com.example.StadiumsSystem.domain.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StadiumRepository extends JpaRepository<Stadium, Integer> {
    //List<Stadium> findAllByEventType(EventType eventType);
}
