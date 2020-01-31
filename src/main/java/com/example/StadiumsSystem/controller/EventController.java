package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Event;
import com.example.StadiumsSystem.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String findAll(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        return "event-list";
    }


    /*
    @GetMapping("/stadium-events/{id}")
    public String getEventTypesOfStadium(@PathVariable("id") Integer id, Model model) {
        Stadium stadium = stadiumService.findById(id);
        List<EventType> eventTypes = stadium.getEventTypes();
        model.addAttribute("stadium", stadium);
        model.addAttribute("eventTypes", eventTypes);
        return "stadium-events";
    }

    @GetMapping("/stadium-create")
    public String createStadiumForm(Model model) {
        model.addAttribute("events", eventTypeService.findAll());
        model.addAttribute("stadium", new Stadium());
        return "stadium-create";
    }

    @PostMapping("/stadium-create")
    public String createStadium(Stadium stadium, Model model) {
        stadiumService.saveStadium(stadium);
        model.addAttribute("stadium", stadium);
        return "redirect:/stadiums";
    }

    @GetMapping("/stadium-delete/{id}")
    public String deleteStadium(@PathVariable("id") Integer id) {
        stadiumService.deleteById(id);
        return "redirect:/stadiums";
    }

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
