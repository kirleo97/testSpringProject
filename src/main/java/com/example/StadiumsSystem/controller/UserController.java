package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Role;
import com.example.StadiumsSystem.domain.User;
import com.example.StadiumsSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list/user-list")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "list/user-list";
    }

    @GetMapping("/update/user/{id}")
    public String userEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roles", Role.values());
        return "update/user-update";
    }
    @PostMapping("/update/user")
    public String saveUser(User user) {
        user.setActive(true);
        userService.saveUser(user);
        return "redirect:/list/user-list";
    }
}