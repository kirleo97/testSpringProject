package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.EventType;
import com.example.StadiumsSystem.domain.Manager;
import com.example.StadiumsSystem.domain.Stadium;
import com.example.StadiumsSystem.service.EventTypeService;
import com.example.StadiumsSystem.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StadiumController {
    private final StadiumService stadiumService;

    @Autowired
    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @GetMapping("/stadiums")
    public String findAll(Model model) {
        List<Stadium> stadiums = stadiumService.findAll();
        model.addAttribute("stadiums", stadiums);
        return "stadium-list";
    }

    @GetMapping("/stadium-events/{id}")
    public String getEventTypesOfStadium(@PathVariable("id") Integer id, Model model) {
        Stadium stadium = stadiumService.findById(id);
        List<EventType> eventTypes = stadium.getEventTypes();
        model.addAttribute("stadium", stadium);
        model.addAttribute("eventTypes", eventTypes);
        return "stadium-events";
    }

    @GetMapping("/stadium-create")
    public String createStadiumForm(Stadium stadium) {
        return "stadium-create";
    }

    @PostMapping("/stadium-create")
    public String createStadium(Stadium stadium, EventTypeService eventTypeService, Model model) {
        stadiumService.saveStadium(stadium);
        model.addAttribute("stadium", stadium);
        List<EventType> events = eventTypeService.findAll();
        model.addAttribute("events", events);
        return "redirect:/stadiums";
    }

    /*
    @GetMapping("/manager-delete/{id}")
    public String deleteManager(@PathVariable("id") Integer id) {
        managerService.deleteById(id);
        return "redirect:/managers";
    }

    @GetMapping("/manager-update/{id}")
    public String updateEventTypeForm(@PathVariable("id") Integer id, Model model) {
        Manager manager = managerService.findById(id);
        model.addAttribute("manager", manager);
        return "manager-update";
    }

    @PostMapping("/manager-update")
    public String updateEventType(Manager manager) {
        managerService.saveManager(manager);
        return "redirect:/managers";
    }
    */
}
