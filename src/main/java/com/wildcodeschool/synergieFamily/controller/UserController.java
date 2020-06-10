package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.User;
import com.wildcodeschool.synergieFamily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> showAllUsers() {

        return userRepository.findAll();
    }

}


