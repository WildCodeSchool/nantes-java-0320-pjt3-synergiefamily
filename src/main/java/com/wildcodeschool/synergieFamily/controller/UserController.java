package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.User;
import com.wildcodeschool.synergieFamily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user-management")
    public String getUserManagement(Model out) {

        out.addAttribute("users", userRepository.findAll());

        return "user-management";
    }

    @GetMapping("/user")
    public String getUserCreation(Model out,
                                  @RequestParam(required = false) Long id) {

        User user = new User();
        if (id != null) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }
        out.addAttribute("user", user);

        return "user-creation";
    }

    @PostMapping("/user")
    public String postUser(@ModelAttribute User newUser) {

        userRepository.save(newUser);
        return "redirect:/user-management";
    }
}


