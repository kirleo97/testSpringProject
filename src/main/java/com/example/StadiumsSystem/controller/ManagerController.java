package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Manager;
import com.example.StadiumsSystem.service.ManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ManagerController {
    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/managers")
    public String findAll(Model model) {
        List<Manager> managers = managerService.findAll();
        model.addAttribute("managers", managers);
        return "manager-list";
    }

    @GetMapping("/manager-create")
    public String createManagerForm(Manager manager) {
        return "manager-create";
    }

    @PostMapping("/manager-create")
    public String createManager(Manager manager, Model model) {
        managerService.saveManager(manager);
        model.addAttribute("manager", manager);
        return "redirect:/managers";
    }
}
