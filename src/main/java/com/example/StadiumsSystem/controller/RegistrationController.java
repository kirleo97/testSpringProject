package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Role;
import com.example.StadiumsSystem.domain.User;
import com.example.StadiumsSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user) {
        if (userService.findAllUsers().size() == 0) {
            user.getRoles().add(Role.ROLE_ADMIN);
        }
        user.getRoles().add(Role.ROLE_USER);
        user.setActive(true);
        userService.saveUser(user);
        return "redirect:/login";
    }
}