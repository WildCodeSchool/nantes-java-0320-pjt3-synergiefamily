package com.wildcodeschool.synergieFamily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    @GetMapping("/filter")
    public String getFilter() {
        return "filter";
    }




}

