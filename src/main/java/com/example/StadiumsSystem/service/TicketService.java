package com.example.StadiumsSystem.service;

import com.example.StadiumsSystem.domain.Event;
import com.example.StadiumsSystem.domain.Sector;
import com.example.StadiumsSystem.domain.Ticket;
import com.example.StadiumsSystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket findById(Integer id) {
        return ticketRepository.getOne(id);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteById(Integer id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> findAllByEvent(Event event) {
        return ticketRepository.findAllByEventOfTicket(event);
    }

    public Ticket findByEventOfTicketAndSectorOfTicketAndSeatNumber(Event eventOfTicket, Sector sectorOfTicket, Integer seatNumber) {
        return ticketRepository.findByEventOfTicketAndSectorOfTicketAndSeatNumber(eventOfTicket, sectorOfTicket, seatNumber);
    }

    public void deleteByEventOfTicketAndSectorOfTicketAndSeatNumber(Event eventOfTicket, Sector sectorOfTicket, Integer seatNumber) {
        ticketRepository.deleteByEventOfTicketAndSectorOfTicketAndSeatNumber(eventOfTicket, sectorOfTicket, seatNumber);
    }
}