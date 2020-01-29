package com.example.StadiumsSystem.repository;

import com.example.StadiumsSystem.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
