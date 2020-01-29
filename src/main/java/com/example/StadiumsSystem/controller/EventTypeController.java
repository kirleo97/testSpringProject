package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.EventType;
import com.example.StadiumsSystem.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventTypeController {
    private final EventTypeService eventTypeService;

    @Autowired
    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping("/eventTypes")
    public String findAll(Model model) {
        List<EventType> eventTypes = eventTypeService.findAll();
        model.addAttribute("eventTypes", eventTypes);
        return "eventType-list";
    }

    @GetMapping("/eventType-create")
    public String createEventTypeForm(EventType eventType) {
        return "eventType-create";
    }

    @PostMapping("/eventType-create")
    public String createEventType(EventType eventType) {
        eventTypeService.saveEventType(eventType);
        return "redirect:/eventTypes";
    }

    @GetMapping("/eventType-delete/{id}")
    public String deleteEventType(@PathVariable("id") Integer id) {
        eventTypeService.deleteById(id);
        return "redirect:/eventTypes";
    }

    @GetMapping("/eventType-update/{id}")
    public String updateEventTypeForm(@PathVariable("id") Integer id, Model model) {
        EventType eventType = eventTypeService.findById(id);
        model.addAttribute("eventType", eventType);
        return "eventType-update";
    }

    @PostMapping("/eventType-update")
    public String updateEventType(EventType eventType) {
        eventTypeService.saveEventType(eventType);
        return "redirect:/eventTypes";
    }
}
