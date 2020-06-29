package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.*;
import com.wildcodeschool.synergieFamily.repository.ActivityLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

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

        activityLeaderRepository.save(activityLeader);
        return "redirect:/activity-leader-management";

    }

    // TODO créer la route pour la modification d'un animateur
    @PostMapping("/activity-leader-modification/{id}")
    public String updateActivityLeader(@ModelAttribute ActivityLeader activityLeader,
                                       @PathVariable Long id,
                                       @PathVariable String lastName,
                                       @PathVariable String firstName,
                                       @PathVariable String phone,
                                       @PathVariable String email,
                                       @PathVariable Date birthdate,
                                       @PathVariable Boolean hasAcar,
                                       @PathVariable String experience,
                                       @PathVariable String availability,
                                       @PathVariable String comment,
                                       @PathVariable Date startDate,
                                       @PathVariable Date endDate,
                                       @PathVariable Location location,
                                       @PathVariable List<Skill> skills,
                                       @PathVariable List<Value> values,
                                       @PathVariable List<Diploma> diplomas,
                                       @PathVariable List<Audience> audiences,
                                       @PathVariable Boolean active,
                                       @PathVariable Boolean draft) {

        Optional<ActivityLeader> optionalActivityLeader = activityLeaderRepository.findById(id);
        if (optionalActivityLeader.isPresent()) {
            activityLeader = optionalActivityLeader.get();
            if (lastName != null) {
                activityLeader.setLastName(lastName);
            }
            if (firstName != null) {
                activityLeader.setFirstName(firstName);
            }
            if (phone != null) {
                activityLeader.setPhone(phone);
            }
            if (email != null) {
                activityLeader.setEmail(email);
            }
            if (birthdate != null) {
                activityLeader.setBirthdate(birthdate);
            }
            if (hasAcar != null) {
                activityLeader.setHasACar(hasAcar);
            }
            if (experience != null) {
                activityLeader.setExperience(experience);
            }
            if (availability != null) {
                activityLeader.setAvailability(availability);
            }
            if (comment != null) {
                activityLeader.setComment(comment);
            }
            if (startDate != null) {
                activityLeader.setStartDate(startDate);
            }
            if (endDate != null) {
                activityLeader.setEndDate(endDate);
            }
            if (location != null) {
                activityLeader.setLocation(location);
            }
            if (skills != null) {
                activityLeader.setSkills(skills);
            }
            if (values != null) {
                activityLeader.setValues(values);
            }
            if (diplomas != null) {
                activityLeader.setDiplomas(diplomas);
            }
            if (audiences != null) {
                activityLeader.setAudiences(audiences);
            }
            if (active != null) {
                activityLeader.setActive(active);
            }
            if (draft != null) {
                activityLeader.setDraft(draft);
            }
        }
        activityLeaderRepository.save(activityLeader);
        return "redirect:/activity-leader-creation";

    }
}
