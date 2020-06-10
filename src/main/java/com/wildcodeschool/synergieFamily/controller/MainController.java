package com.wildcodeschool.synergieFamily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    @GetMapping("/activity-leader-creation")
    public String getActivityLeaderCreation() {
        return "activity-leader-creation";
    }

    @GetMapping("/filter")
    public String getFilter() {
        return "filter";
    }


}

