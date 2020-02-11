package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Role;
import com.example.StadiumsSystem.domain.User;
import com.example.StadiumsSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
    public String addUser(User user, Model model) {
        List<User> users = userService.findAllUsers();
        if (users.size() == 0) {
            user.setRoles(new HashSet<>(Arrays.asList(Role.ADMIN, Role.USER)));
        } else {
            user.setRoles(new HashSet<>(Arrays.asList(Role.USER)));
        }
        user.setActive(true);
        userService.saveUser(user);
        return "redirect:/login";
    }
}