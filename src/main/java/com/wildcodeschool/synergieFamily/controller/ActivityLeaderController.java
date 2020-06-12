package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.ActivityLeader;
import com.wildcodeschool.synergieFamily.repository.ActivityLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ActivityLeaderController {

    @Autowired
    private ActivityLeaderRepository activityLeaderRepository;

    @GetMapping("/activity-leader-creation")
    public String getActivityLeaderCreation() {

        return "activity-leader-creation";
    }

    @GetMapping("/activity-leader-management")
    public String getActivityLeaderManagement(Model out) {

        out.addAttribute("activityLeaders", activityLeaderRepository.findAll());

        return "activity-leader-management";
    }

    @GetMapping("/activityLeaders")
    public List<ActivityLeader> showAllActivityLeaders() {

        return activityLeaderRepository.findAll();
    }

    @GetMapping("/activityLeader")
    public List<ActivityLeader> showActivityLeadersByLastNameFirstNamePhoneEmail(@PathVariable String lastName,
                                                                                 @PathVariable String firstName) {

        return activityLeaderRepository.findAllByLastNameContainingAndFirstNameContaining(lastName, firstName);
    }
}
