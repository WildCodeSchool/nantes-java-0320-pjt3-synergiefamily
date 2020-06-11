package com.wildcodeschool.synergieFamily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActivityLeaderController {

    @GetMapping("/activity-leader-creation")
    public String getActivityLeaderCreation() {

        return "activity-leader-creation";
    }
}
