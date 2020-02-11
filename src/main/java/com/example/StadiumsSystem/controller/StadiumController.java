package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Stadium;
import com.example.StadiumsSystem.service.EventTypeService;
import com.example.StadiumsSystem.service.StadiumService;
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
public class StadiumController {
    private final StadiumService stadiumService;
    private final EventTypeService eventTypeService;

    @Autowired
    public StadiumController(StadiumService stadiumService, EventTypeService eventTypeService) {
        this.stadiumService = stadiumService;
        this.eventTypeService = eventTypeService;
    }

    @GetMapping("/list/stadiums")
    public String findAll(Model model) {
        List<Stadium> stadiums = stadiumService.findAll();
        model.addAttribute("stadiums", stadiums);
        return "list/stadium-list";
    }

    @GetMapping("/create/stadium-create")
    public String createStadiumForm(Model model) {
        model.addAttribute("events", eventTypeService.findAll());
        model.addAttribute("stadium", new Stadium());
        return "create/stadium-create";
    }

    @PostMapping("/create/stadium-create")
    public String createStadium(@Valid Stadium stadium, BindingResult bindingResult, Model model) {
        model.addAttribute("events", eventTypeService.findAll());
        if (!stadiumService.isValidationForCreateStadiumSuccessful(stadium, bindingResult)) {
            return "create/stadium-create";
        }
        stadiumService.saveStadium(stadium);
        return "redirect:/list/stadiums";
    }

    @GetMapping("/delete/stadium-delete/{id}")
    public String deleteStadium(@PathVariable("id") Integer id) {
        stadiumService.deleteById(id);
        return "redirect:/list/stadiums";
    }

    @GetMapping("/update/stadium-update/{id}")
    public String updateStadiumForm(@PathVariable("id") Integer id, Model model) {
        Stadium stadium = stadiumService.findById(id);
        model.addAttribute("stadium", stadium);
        model.addAttribute("events", eventTypeService.findAll());
        return "update/stadium-update";
    }

    @PostMapping("/update/stadium-update")
    public String updateStadium(@Valid Stadium stadium, BindingResult bindingResult, Model model) {
        model.addAttribute("events", eventTypeService.findAll());
        if (!stadiumService.isValidationForUpdateStadiumSuccessful(stadium, bindingResult)) {
            return "update/stadium-update";
        }
        stadiumService.saveStadium(stadium);
        return "redirect:/list/stadiums";
    }
}
