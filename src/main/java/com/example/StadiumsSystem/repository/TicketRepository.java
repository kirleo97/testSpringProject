package com.example.StadiumsSystem.repository;

import com.example.StadiumsSystem.domain.Event;
import com.example.StadiumsSystem.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByEventOfTicket(Event eventOfTicket);
}
