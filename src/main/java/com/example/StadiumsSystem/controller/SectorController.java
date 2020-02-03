package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Sector;
import com.example.StadiumsSystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SectorController {
    private final SectorService sectorService;
    private final StadiumService stadiumService;

    @Autowired
    public SectorController(SectorService sectorService, StadiumService stadiumService) {
        this.sectorService = sectorService;
        this.stadiumService = stadiumService;
    }

    @GetMapping("/sectors")
    public String findAll(Model model) {
        List<Sector> sectors = sectorService.findAll();
        model.addAttribute("sectors", sectors);
        return "sector-list";
    }

    @GetMapping("/sector-create")
    public String createSectorForm(Model model) {
        model.addAttribute("sector", new Sector());
        model.addAttribute("stadiums", stadiumService.findAll());
        return "sector-create";
    }

    @PostMapping("/sector-create")
    public String createSector(Sector sector, Model model) {
        sectorService.saveSector(sector);
        return "redirect:/sectors";
    }

    @GetMapping("/sector-delete/{id}")
    public String deleteSector(@PathVariable("id") Integer id) {
        sectorService.deleteById(id);
        return "redirect:/sectors";
    }

    @GetMapping("/sector-update/{id}")
    public String updateSectorForm(@PathVariable("id") Integer id, Model model) {
        Sector sector = sectorService.findById(id);
        model.addAttribute("sector", sector);
        model.addAttribute("stadiums", stadiumService.findAll());
        return "sector-update";
    }

    @PostMapping("/sector-update")
    public String updateSector(Sector sector) {
        sectorService.saveSector(sector);
        return "redirect:/sectors";
    }
}
