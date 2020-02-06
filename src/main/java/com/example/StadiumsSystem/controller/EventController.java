package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Event;
import com.example.StadiumsSystem.domain.EventType;
import com.example.StadiumsSystem.service.EventService;
import com.example.StadiumsSystem.service.EventTypeService;
import com.example.StadiumsSystem.service.ManagerService;
import com.example.StadiumsSystem.service.StadiumService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("events", eventTypeService.findAll());
        return "event-create";
    }

    /*@PostMapping("/event-create")
    public String createEventForm(EventType eventType, Model model) {
        Integer id = eventType.getId();
        return "redirect:/event-create/byEventType/{id}";
    }*/

    @GetMapping("/event-create/byEventType/{id}")
    public String createEventByEventTypeForm(@PathVariable Integer id, Model model) {
        model.addAttribute("event", new Event());
        EventType eventType = eventTypeService.findById(id);
        model.addAttribute("eventType", eventType);
        model.addAttribute("stadiums", stadiumService.findAllStadiumsByEventType(eventType));
        model.addAttribute("managers", managerService.findAll());
        return "event-createByEventType";
    }

    @PostMapping("/event-create/byEventType/{id}")
    public String createEventByEventTypeForm(@PathVariable Integer id, Event event) {
        event.setEventType(eventTypeService.findById(id));
        eventService.saveEvent(event);
        return "redirect:/events";
    }


    @GetMapping("/event-delete/{id}")
    public String deleteEvent(@PathVariable("id") Integer id) {
        eventService.deleteById(id);
        return "redirect:/events";
    }


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
    public String updateEvent(Event event) {
        //event.setAllDatesForEvent(event.parseDateOfEventFromDefaultPattern(date), event.parseDateOfPreparationPeriodFromDefaultPattern(startOfPreparation), event.parseDateOfPreparationPeriodFromDefaultPattern(endOfDismantle));
        eventService.saveEvent(event);
        return "redirect:/events";
    }
}