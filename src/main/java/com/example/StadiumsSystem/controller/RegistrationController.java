package com.example.StadiumsSystem.controller;

import com.example.StadiumsSystem.domain.Role;
import com.example.StadiumsSystem.domain.User;
import com.example.StadiumsSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult) {
        if (!userService.isRegistrationFormRight(user, bindingResult)) {
            return "registration";
        }
        if (userService.findAllUsers().size() == 0) {
            user.getRoles().add(Role.ROLE_ADMIN);
        }
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setActive(true);
        userService.saveUser(user);
        return "redirect:/login";
    }
}