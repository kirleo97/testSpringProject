package com.example.StadiumsSystem.repository;

import com.example.StadiumsSystem.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
