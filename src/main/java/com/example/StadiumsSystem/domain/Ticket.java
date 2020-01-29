package com.example.StadiumsSystem.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TICKET_ID", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "TICKET_EVENT", nullable = false)
    private Event eventOfTicket;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "TICKET_SECTOR", nullable = false)
    private Sector sectorOfTicket;

    @Column(name = "TICKET_SEATNUMBER", nullable = false)
    private int seatNumber;

    @Column(name = "TICKET_COST", nullable = false)
    private BigDecimal ticketCost;

    @Column(name = "TICKET_ISFREE", nullable = false)
    @Autowired
    private boolean isTicketFree = true;

    public Ticket() {
    }

    @Autowired
    public Ticket(Event eventOfTicket, Sector sectorOfTicket, int seatNumber, BigDecimal ticketCost) {
        this.eventOfTicket = eventOfTicket;
        this.sectorOfTicket = sectorOfTicket;
        this.seatNumber = seatNumber;
        this.ticketCost = ticketCost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEventOfTicket() {
        return eventOfTicket;
    }

    public void setEventOfTicket(Event eventOfTicket) {
        this.eventOfTicket = eventOfTicket;
    }

    public Sector getSectorOfTicket() {
        return sectorOfTicket;
    }

    public void setSectorOfTicket(Sector sectorOfTicket) {
        this.sectorOfTicket = sectorOfTicket;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public BigDecimal getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(BigDecimal ticketCost) {
        this.ticketCost = ticketCost;
    }

    public boolean isTicketFree() {
        return isTicketFree;
    }

    public void setTicketFree(boolean ticketFree) {
        isTicketFree = ticketFree;
    }
}