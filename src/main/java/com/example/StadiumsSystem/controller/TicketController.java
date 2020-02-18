package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Event;
import com.example.StadiumsSystem.domain.Sector;
import com.example.StadiumsSystem.domain.Ticket;
import com.example.StadiumsSystem.service.EventService;
import com.example.StadiumsSystem.service.SectorService;
import com.example.StadiumsSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class TicketController {
    private final TicketService ticketService;
    private final EventService eventService;
    private final SectorService sectorService;

    @Autowired
    public TicketController(TicketService ticketService, EventService eventService, SectorService sectorService) {
        this.ticketService = ticketService;
        this.eventService = eventService;
        this.sectorService = sectorService;
    }

    @GetMapping("/tickets")
    public String getFormToSelectEventForTickets(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "list/ticket-list";
    }

    @GetMapping("/tickets/event/{id}")
    public String getTicketsByEvent(@PathVariable Integer id, @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size, Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Event event = eventService.findById(id);
        model.addAttribute("event", event);
        List<Ticket> ticketList = ticketService.findAllByEvent(event);
        Page<Ticket> bookPage = ticketService.findPaginated(ticketList, PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("bookPage", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("tickets", ticketList);
        model.addAttribute("IdOfEvent", id);
        return "list/tickets-byEvent";
    }

    @GetMapping("/create/ticket-create/event/{id}")
    public String createTicketForm(@PathVariable Integer id, Model model) {
        Event event = eventService.findById(id);
        model.addAttribute("event", event);
        model.addAttribute("IdOfEvent", id);
        model.addAttribute("sectors", sectorService.findSectorsByStadium(event.getStadiumOfEvent()));
        return "create/ticket-create";
    }

    @PostMapping("/create/ticket-create/event/{id}")
    public String createEvent(@PathVariable Integer id,
                              @RequestParam Integer sectorId,
                              @Valid @RequestParam Integer fromSeatNumber,
                              @RequestParam Integer toSeatNumber,
                              @RequestParam Integer cost,
                              Model model) {
        model.addAttribute("IdOfEvent", id);
        Event event = eventService.findById(id);
        Sector sector = sectorService.findById(sectorId);
        fromSeatNumber = ticketService.ckeckFromSeatNumber(fromSeatNumber);
        toSeatNumber = ticketService.ckeckToSeatNumber(toSeatNumber, sector);
        cost = ticketService.checkCost(cost);
        Ticket ticket;
        for (int i = fromSeatNumber; i <= toSeatNumber; i++) {
            if (!ticketService.isTicketWithEventAndSectorAndNumberExist(event, sector, i)) {
                ticket = new Ticket(event, sector, i, new BigDecimal(cost));
                ticketService.saveTicket(ticket);
            }
        }
        return "redirect:/tickets/event/{id}";
    }

    @GetMapping("/delete/ticket-delete/{eventId}/{ticketId}")
    public String deleteDefinedTicket(@PathVariable Integer eventId, @PathVariable Integer ticketId) {
        ticketService.deleteById(ticketId);
        return "redirect:/tickets/event/{eventId}";
    }

    @GetMapping("/delete/ticket-delete/event/{id}")
    public String getDeleteFormOfSomeTickets(@PathVariable Integer id, Model model) {
        Event event = eventService.findById(id);
        model.addAttribute("IdOfEvent", id);
        model.addAttribute("event", event);
        model.addAttribute("sectors", sectorService.findSectorsByStadium(event.getStadiumOfEvent()));
        return "delete/ticket-delete";
    }

    @PostMapping("/delete//ticket-delete/event/{id}")
    public String deleteSomeTickets(@PathVariable Integer id,
                                    @RequestParam Integer sectorId,
                                    @RequestParam Integer fromSeatNumber,
                                    @RequestParam Integer toSeatNumber,
                                    Model model) {
        model.addAttribute("IdOfEvent", id);
        Sector sector = sectorService.findById(sectorId);
        Event event = eventService.findById(id);
        fromSeatNumber = ticketService.ckeckFromSeatNumber(fromSeatNumber);
        toSeatNumber = ticketService.ckeckToSeatNumber(toSeatNumber, sector);
        Ticket ticket;
        for (int i = fromSeatNumber; i <= toSeatNumber; i++) {
            ticket = ticketService.findByEventOfTicketAndSectorOfTicketAndSeatNumber(event, sector, i);
            if (ticket != null) {
                ticketService.deleteById(ticket.getId());
            }
        }
        return "redirect:/tickets/event/{id}";
    }

    @GetMapping("/update/ticket-update/event/{eventId}/{ticketId}")
    public String updateTicketForm(@PathVariable Integer eventId, @PathVariable Integer ticketId, Model model) {
        Event event = eventService.findById(eventId);
        model.addAttribute("IdOfEvent", eventId);
        model.addAttribute("IdOfTicket", ticketId);
        model.addAttribute("event", event);
        model.addAttribute("ticket", ticketService.findById(ticketId));
        model.addAttribute("sectors", sectorService.findSectorsByStadium(event.getStadiumOfEvent()));
        return "update/ticket-update";
    }

    @PostMapping("/update/ticket-update/event/{eventId}/{ticketId}")
    public String updateEvent(@PathVariable Integer eventId,
                              @PathVariable Integer ticketId,
                              @RequestParam Integer sectorId,
                              @RequestParam Integer numberOfTicketSeat,
                              @RequestParam Integer cost,
                              Model model) {
        Sector sector = sectorService.findById(sectorId);
        Ticket ticket = ticketService.findById(ticketId);
        model.addAttribute("IdOfEvent", eventId);
        model.addAttribute("IdOfTicket", ticketId);
        if (ticketService.findByEventOfTicketAndSectorOfTicketAndSeatNumber(eventService.findById(eventId), sector, numberOfTicketSeat) != null) {
            return "redirect:/tickets/event/{eventId}";
        }
        cost = ticketService.checkCost(cost);
        numberOfTicketSeat = ticketService.checkSeatNumber(sector, numberOfTicketSeat);
        ticket.setSectorOfTicket(sector);
        ticket.setSeatNumber(numberOfTicketSeat);
        ticket.setTicketCost(new BigDecimal(cost));
        ticketService.saveTicket(ticket);
        return "redirect:/tickets/event/{eventId}";
    }

    @PostMapping("buy/ticket-buy/{eventId}/{ticketId}")
    public String buyTicket(@PathVariable Integer eventId, @PathVariable Integer ticketId, Model model) {
        model.addAttribute("IdOfEvent", eventId);
        model.addAttribute("IdOfTicket", ticketId);
        Ticket ticket = ticketService.findById(ticketId);
        ticket.setTicketBought(true);
        ticketService.saveTicket(ticket);
        return "redirect:/tickets/event/{eventId}";
    }
}