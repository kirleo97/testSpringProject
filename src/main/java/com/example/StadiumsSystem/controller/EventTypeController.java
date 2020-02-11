package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.EventType;
import com.example.StadiumsSystem.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EventTypeController {
    private final EventTypeService eventTypeService;

    @Autowired
    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping("/list/eventTypes")
    public String findAll(Model model) {
        List<EventType> eventTypes = eventTypeService.findAll();
        model.addAttribute("eventTypes", eventTypes);
        return "list/eventType-list";
    }

    @GetMapping("/create/eventType-create")
    public String createEventTypeForm(Model model) {
        model.addAttribute("eventType", new EventType());
        return "create/eventType-create";
    }

    @PostMapping("/create/eventType-create")
    public String createEventType(@Valid EventType eventType, BindingResult bindingResult) {
        if (!eventTypeService.isValidationForEventTypeSuccessful(eventType, bindingResult)) {
            return "create/eventType-create";
        }
        eventTypeService.saveEventType(eventType);
        return "redirect:/list/eventTypes";
    }

    @GetMapping("/delete/eventType-delete/{id}")
    public String deleteEventType(@PathVariable("id") Integer id) {
        eventTypeService.deleteById(id);
        return "redirect:/list/eventTypes";
    }

    @GetMapping("/update/eventType-update/{id}")
    public String updateEventTypeForm(@PathVariable("id") Integer id, Model model) {
        EventType eventType = eventTypeService.findById(id);
        model.addAttribute("eventType", eventType);
        return "update/eventType-update";
    }

    @PostMapping("/update/eventType-update")
    public String updateEventType(@Valid EventType eventType, BindingResult bindingResult) {
        if (!eventTypeService.isValidationForEventTypeSuccessful(eventType, bindingResult)) {
            return "update/eventType-update";
        }
        eventTypeService.saveEventType(eventType);
        return "redirect:/list/eventTypes";
    }
}