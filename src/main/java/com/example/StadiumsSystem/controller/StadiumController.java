package com.example.StadiumsSystem.controller;

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
    private final EventTypeService eventTypeService;

    @Autowired
    public StadiumController(StadiumService stadiumService, EventTypeService eventTypeService) {
        this.stadiumService = stadiumService;
        this.eventTypeService = eventTypeService;
    }

    @GetMapping("/stadiums")
    public String findAll(Model model) {
        List<Stadium> stadiums = stadiumService.findAll();
        model.addAttribute("stadiums", stadiums);
        return "stadium-list";
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
}
