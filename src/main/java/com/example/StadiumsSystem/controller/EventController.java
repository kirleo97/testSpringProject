package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Event;
import com.example.StadiumsSystem.service.EventService;
import com.example.StadiumsSystem.service.EventTypeService;
import com.example.StadiumsSystem.service.ManagerService;
import com.example.StadiumsSystem.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class EventController {
    private final EventService eventService;
    private final EventTypeService eventTypeService;
    private final StadiumService stadiumService;
    private final ManagerService managerService;

    @Autowired
    public EventController(EventService eventService, EventTypeService eventTypeService, StadiumService stadiumService, ManagerService managerService) {
        this.eventService = eventService;
        this.eventTypeService = eventTypeService;
        this.stadiumService = stadiumService;
        this.managerService = managerService;
    }

    @GetMapping("/events")
    public String findAll(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        return "event-list";
    }


    @GetMapping("/event-create")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("eventTypes", eventTypeService.findAll());
        model.addAttribute("stadiums", stadiumService.findAll());
        model.addAttribute("managers", managerService.findAll());
        return "event-create";
    }

    @PostMapping("/event-create")
    public String createEvent(Event event, @RequestParam String date, @RequestParam String startOfPreparation, @RequestParam String endOfDismantle, Model model) {
        event.setDateOfEvent(ZonedDateTime.of(LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")), ZoneId.of("Europe/Moscow")));
        event.setStartOfPreparationOfStadium(LocalDate.parse(startOfPreparation, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        event.setEndOfDismantleOfStadium(LocalDate.parse(endOfDismantle, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        eventService.saveEvent(event);
        model.addAttribute("event", event);
        return "redirect:/events";
    }


    @GetMapping("/event-delete/{id}")
    public String deleteEvent(@PathVariable("id") Integer id) {
        eventService.deleteById(id);
        return "redirect:/events";
    }

    /*
    @GetMapping("/stadium-update/{id}")
    public String updateStadiumForm(@PathVariable("id") Integer id, Model model) {
        Stadium stadium = stadiumService.findById(id);
        model.addAttribute("stadium", stadium);
        model.addAttribute("events", eventTypeService.findAll());
        return "stadium-update";
    }

    @PostMapping("/stadium-update")
    public String updateEventType(Stadium stadium) {
        stadiumService.saveStadium(stadium);
        return "redirect:/stadiums";
    }
    */
}
