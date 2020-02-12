package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Sector;
import com.example.StadiumsSystem.service.SectorService;
import com.example.StadiumsSystem.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SectorController {
    private final SectorService sectorService;
    private final StadiumService stadiumService;

    @Autowired
    public SectorController(SectorService sectorService, StadiumService stadiumService) {
        this.sectorService = sectorService;
        this.stadiumService = stadiumService;
    }

    @GetMapping("/list/sectors")
    public String findAll(Model model) {
        model.addAttribute("sectors", sectorService.findAll());
        return "list/sector-list";
    }

    @GetMapping("/create/sector-create")
    public String createSectorForm(Model model) {
        model.addAttribute("sector", new Sector());
        model.addAttribute("stadiums", stadiumService.findAll());
        return "create/sector-create";
    }

    @PostMapping("/create/sector-create")
    public String createSector(@Valid Sector sector, BindingResult bindingResult, Model model) {
        model.addAttribute("stadiums", stadiumService.findAll());
        model.addAttribute("sector", sector);
        if (bindingResult.hasErrors()) {
            return "create/sector-create";
        }
        sectorService.checkValidationForSector(sector, bindingResult);
        if (bindingResult.hasErrors()) {
            return "create/sector-create";
        }
        sectorService.saveSector(sector);
        return "redirect:/list/sectors";
    }

    @GetMapping("/delete/sector-delete/{id}")
    public String deleteSector(@PathVariable("id") Integer id) {
        sectorService.deleteById(id);
        return "redirect:/list/sectors";
    }

    @GetMapping("/update/sector-update/{id}")
    public String updateSectorForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("sector", sectorService.findById(id));
        model.addAttribute("stadiums", stadiumService.findAll());
        return "update/sector-update";
    }

    @PostMapping("/update/sector-update")
    public String updateSector(@Valid Sector sector, BindingResult bindingResult, Model model) {
        model.addAttribute("sector", sector);
        model.addAttribute("stadiums", stadiumService.findAll());
        if (bindingResult.hasErrors()) {
            return "update/sector-update";
        }
        sectorService.checkValidationForSector(sector, bindingResult);
        if (bindingResult.hasErrors()) {
            return "update/sector-update";
        }
        sectorService.saveSector(sector);
        return "redirect:/list/sectors";
    }
}