package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.ActivityLeader;
import com.wildcodeschool.synergieFamily.repository.ActivityLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ActivityLeaderController {

    @Autowired
    private ActivityLeaderRepository activityLeaderRepository;

    @GetMapping("/activity-leader-creation")
    public String getActivityLeaderCreation(Model out) {

        ActivityLeader activityLeader = new ActivityLeader();
        out.addAttribute("activityLeader", activityLeader);
        return "activity-leader-creation";
    }

    @PostMapping("/activity-leader-creation")
    public String postForm(@ModelAttribute ActivityLeader activityLeader) {

        activityLeaderRepository.save(activityLeader);
        return "redirect:/activity-leader-creation";
    }
}
