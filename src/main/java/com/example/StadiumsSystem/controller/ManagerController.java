package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Manager;
import com.example.StadiumsSystem.service.ManagerService;

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
public class ManagerController {
    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/list/managers")
    public String findAll(Model model) {
        List<Manager> managers = managerService.findAll();
        model.addAttribute("managers", managers);
        return "list/manager-list";
    }

    @GetMapping("/create/manager-create")
    public String createManagerForm(Model model) {
        model.addAttribute("manager", new Manager());
        return "create/manager-create";
    }

    @PostMapping("/create/manager-create")
    public String createManager(@Valid Manager manager, BindingResult bindingResult) {
        if (!managerService.isValidationForManagerSuccessful(manager, bindingResult)) {
            return "create/manager-create";
        }
        managerService.saveManager(manager);
        return "redirect:/list/managers";
    }

    @GetMapping("/delete/manager-delete/{id}")
    public String deleteManager(@PathVariable("id") Integer id) {
        managerService.deleteById(id);
        return "redirect:/list/managers";
    }

    @GetMapping("/update/manager-update/{id}")
    public String updateEventTypeForm(@PathVariable("id") Integer id, Model model) {
        Manager manager = managerService.findById(id);
        model.addAttribute("manager", manager);
        return "update/manager-update";
    }

    @PostMapping("/update/manager-update")
    public String updateEventType(@Valid Manager manager, BindingResult bindingResult) {
        if (!managerService.isValidationForManagerSuccessful(manager, bindingResult)) {
            return "update/manager-update";
        }
        managerService.saveManager(manager);
        return "redirect:/list/managers";
    }
}