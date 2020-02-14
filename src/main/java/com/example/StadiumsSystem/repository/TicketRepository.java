package com.example.StadiumsSystem.repository;

import com.example.StadiumsSystem.domain.Event;
import com.example.StadiumsSystem.domain.Sector;
import com.example.StadiumsSystem.domain.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByEventOfTicket(Event eventOfTicket);
    Ticket findByEventOfTicketAndSectorOfTicketAndSeatNumber(Event eventOfTicket, Sector sectorOfTicket, Integer seatNumber);
}