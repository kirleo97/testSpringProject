package com.example.StadiumsSystem.service;

import com.example.StadiumsSystem.domain.User;
import com.example.StadiumsSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUserName(userName);
    }

    public boolean isRegistrationFormRight(User user, BindingResult bindingResult) {
        User checkUser = findUserByUserName(user.getUserName());
        if (checkUser != null) {
            if (!checkUser.getId().equals(user.getId())) {
                bindingResult.addError(new FieldError("user", "userName", "A user with this name already exists [" + user.getUserName() + "]."));
            }
        }
        return !bindingResult.hasErrors();
    }
}