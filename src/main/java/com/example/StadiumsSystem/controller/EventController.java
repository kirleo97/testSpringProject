package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Event;
import com.example.StadiumsSystem.service.EventService;
import com.example.StadiumsSystem.service.EventTypeService;
import com.example.StadiumsSystem.service.ManagerService;
import com.example.StadiumsSystem.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

    @GetMapping("/list/events")
    public String findAll(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "list/event-list";
    }

    @GetMapping("/create/event-create")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("eventTypes", eventTypeService.findAll());
        model.addAttribute("stadiums", stadiumService.findAll());
        model.addAttribute("managers", managerService.findAll());
        return "create/event-create";
    }

    @PostMapping("/create/event-create")
    public String createEvent(@Valid Event event, BindingResult bindingResult, Model model) {
        model.addAttribute("eventTypes", eventTypeService.findAll());
        model.addAttribute("stadiums", stadiumService.findAll());
        model.addAttribute("managers", managerService.findAll());
        model.addAttribute("event", event);
        if (bindingResult.hasErrors()) {
            return "create/event-create";
        }
        eventService.checkValidationFormForEvent(event, bindingResult);
        if (!event.getStadiumOfEvent().getEventTypes().contains(event.getEventType())) {
            bindingResult.addError(new FieldError("event", "stadiumOfEvent", "The selected stadium does not have this type of event. Possible stadiums for this type of event: " + stadiumService.findAllStadiumsByEventType(event.getEventType())));
        }
        if (bindingResult.hasErrors()) {
            return "create/event-create";
        }
        eventService.saveEvent(event);
        return "redirect:/list/events";
    }

    @GetMapping("/delete/event-delete/{id}")
    public String deleteEvent(@PathVariable("id") Integer id) {
        eventService.deleteById(id);
        return "redirect:/list/events";
    }

    @GetMapping("/update/event-update/{id}")
    public String updateEventForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        model.addAttribute("typesOfEvent", eventTypeService.findAll());
        model.addAttribute("stadiums", stadiumService.findAll());
        model.addAttribute("managers", managerService.findAll());
        return "update/event-update";
    }

    @PostMapping("/update/event-update")
    public String updateEvent(@Valid Event event, BindingResult bindingResult, Model model) {
        model.addAttribute("typesOfEvent", eventTypeService.findAll());
        model.addAttribute("stadiums", stadiumService.findAll());
        model.addAttribute("managers", managerService.findAll());
        if (bindingResult.hasErrors()) {
            return "update/event-update";
        }
        if (!event.getStadiumOfEvent().getEventTypes().contains(event.getEventType())) {
            bindingResult.addError(new FieldError("event", "stadiumOfEvent", "The selected stadium does not have this type of event. Possible stadiums for this type of event: " + stadiumService.findAllStadiumsByEventType(event.getEventType())));
        }
        eventService.checkValidationFormForEvent(event, bindingResult);
        if (bindingResult.hasErrors()) {
            return "update/event-update";
        }
        eventService.saveEvent(event);
        return "redirect:/list/events";
    }
}