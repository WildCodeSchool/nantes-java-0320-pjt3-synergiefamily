package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.Role;
import com.wildcodeschool.synergieFamily.entity.User;
import com.wildcodeschool.synergieFamily.repository.RoleRepository;
import com.wildcodeschool.synergieFamily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/user-management")
    public String getUserManagement(Model out) {

        out.addAttribute("users", userRepository.findAll());

        return "user-management";
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {

        return userRepository.findAll();
    }




}


