package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.*;
import com.wildcodeschool.synergieFamily.repository.ActivityLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class ActivityLeaderController {

    @Autowired
    private ActivityLeaderRepository activityLeaderRepository;

    @GetMapping("/activity-leader-creation")
    public String getActivityLeaderCreation(Model out) {

        ActivityLeader activityLeader = new ActivityLeader();
        out.addAttribute("activityLeader", activityLeader);
        out.addAttribute("editable", true);
        return "activity-leader-creation";
    }

    @GetMapping("/activity-leader-management")
    public String showAllActivityLeaders(Model out) {

        out.addAttribute("activityLeaders", activityLeaderRepository.findAll());
        return "activity-leader-management";
    }

    @GetMapping("/activity-leaders-search")
    public String showActivityLeadersByLastNameFirstNamePhoneEmail(Model out,
                                                                   @RequestParam String search) {

        out.addAttribute("activityLeaders", activityLeaderRepository.findByLastNameContainingOrFirstNameContainingOrEmailContaining(search, search, search));
        return "activity-leader-management";
    }

    @PostMapping("/activity-leader-creation")
    public String postForm(@ModelAttribute ActivityLeader activityLeader) {

        String skillList = activityLeader.getSkillList();
        String[] skills = skillList.split(",");
        for (String skill : skills) {
            Skill skillItem = new Skill(skill);
            activityLeader.getSkills().add(skillItem);
        }

        activityLeader = activityLeaderRepository.save(activityLeader);
        return "redirect:/activity-leader-modification/" + activityLeader.getId();

    }

    @GetMapping("/activity-leader-modification/{id}")
    public String getActivityLeaderModification(Model out,
                                                @PathVariable Long id) {

        Optional<ActivityLeader> optionalActivityLeader = activityLeaderRepository.findById(id);
        if (optionalActivityLeader.isPresent()) {
            ActivityLeader activityLeader = optionalActivityLeader.get();
            out.addAttribute("activityLeader", activityLeader);
            out.addAttribute("editable", true);
        }
        return "activity-leader-creation";
    }

    @GetMapping("/activity-leader-showcard/{id}")
    public String getActivityLeaderShowCard(Model out,
                                                @PathVariable Long id) {

        Optional<ActivityLeader> optionalActivityLeader = activityLeaderRepository.findById(id);
        if (optionalActivityLeader.isPresent()) {
            ActivityLeader activityLeader = optionalActivityLeader.get();
            out.addAttribute("activityLeader", activityLeader);
            out.addAttribute("editable", false);
        }
        return "activity-leader-creation";
    }
}