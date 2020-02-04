package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Event;
import com.example.StadiumsSystem.domain.Ticket;
import com.example.StadiumsSystem.service.EventService;
import com.example.StadiumsSystem.service.SectorService;
import com.example.StadiumsSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;

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
        return "ticket-list";
    }

    @GetMapping("/tickets/event/{id}")
    public String getTicketsByEvent(@PathVariable Integer id, Model model) {
        Event event = eventService.findById(id);
        model.addAttribute("event", event);
        List<Ticket> tickets = ticketService.findByEvent(event);
        model.addAttribute("tickets", tickets);
        return "tickets-byEvent";
    }

    @GetMapping("/ticket-create/event/{id}")
    public String createTicketForm(@PathVariable Integer id, Model model) {
        Event event = eventService.findById(id);
        model.addAttribute("event", event);
        model.addAttribute("sectors", sectorService.findSectorsByStadium(event.getStadiumOfEvent()));
        return "ticket-create";
    }

    @PostMapping("/ticket-create/event/{id}")
    public String createEvent(@PathVariable Integer id,
                              @RequestParam Integer sectorId,
                              @RequestParam Integer fromSeatNumber,
                              @RequestParam Integer toSeatNumber,
                              @RequestParam Integer cost) {
        for (int i = fromSeatNumber; i <=toSeatNumber; i++) {
            ticketService.saveTicket(new Ticket(eventService.findById(id), sectorService.findById(sectorId), i, new BigDecimal(cost)));
        }
        return "redirect:/tickets/event/{id}";
    }

    @GetMapping("/ticket-delete/{id}")
    public String deleteTicket(@PathVariable Integer id) {
        ticketService.deleteById(id);
        return "redirect:/tickets";
    }

    /*
    @GetMapping("/event-update/{id}")
    public String updateEventForm(@PathVariable("id") Integer id, Model model) {
        Event event = eventService.findById(id);
        model.addAttribute("event", event);
        model.addAttribute("typesOfEvent", eventTypeService.findAll());
        model.addAttribute("stadiums", stadiumService.findAll());
        model.addAttribute("managers", managerService.findAll());
        return "event-update";
    }

    @PostMapping("/event-update")
    public String updateEvent(Event event, @RequestParam String date, @RequestParam String startOfPreparation, @RequestParam String endOfDismantle) {
        event.setAllDatesForEvent(event.parseDateOfEventFromDefaultPattern(date), event.parseDateOfPreparationPeriodFromDefaultPattern(startOfPreparation), event.parseDateOfPreparationPeriodFromDefaultPattern(endOfDismantle));
        eventService.saveEvent(event);
        return "redirect:/events";
    }
    */
}
