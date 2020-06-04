package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    //TODO DEPLACER LA METHODE PLUS TARD VOIR AVEC ALAN
    @GetMapping("/filter")
    public String getFilter(){
        return "filter";
    }

}
