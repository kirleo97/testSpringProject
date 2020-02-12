package com.example.StadiumsSystem.repository;

import com.example.StadiumsSystem.domain.Event;
import com.example.StadiumsSystem.domain.EventType;
import com.example.StadiumsSystem.domain.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAllByStadiumOfEvent(Stadium stadiumOfEvent);
}