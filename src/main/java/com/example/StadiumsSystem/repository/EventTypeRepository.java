package com.example.StadiumsSystem.repository;

import com.example.StadiumsSystem.domain.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Integer> {
    EventType findByEventTypeName(String eventTypeName);
}