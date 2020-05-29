package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;
}
