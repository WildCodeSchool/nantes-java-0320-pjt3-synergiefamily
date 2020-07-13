package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.*;
import com.wildcodeschool.synergieFamily.repository.*;
import com.wildcodeschool.synergieFamily.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class ActivityLeaderController {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DiplomaRepository diplomaRepository;

    @Autowired
    private AudienceRepository audienceRepository;

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private ActivityLeaderRepository activityLeaderRepository;

    @GetMapping("/activity-leader-creation")
    public String getActivityLeaderCreation(Model out) {

        ActivityLeader activityLeader = new ActivityLeader();
        out.addAttribute("activityLeader", activityLeader);
        out.addAttribute("valuesList", valueRepository.findAll());
        out.addAttribute("audiencesList", audienceRepository.findAll());
        out.addAttribute("diplomasList", diplomaRepository.findAll());
        out.addAttribute("editable", true);

        return "activity-leader-creation";
    }

    @GetMapping("/activity-leader-management")
    public String showAllActivityLeaders(Model out) {

        out.addAttribute("activityLeaders", activityLeaderRepository.findAllActive());
        return "activity-leader-management";
    }

    @GetMapping("/activity-leaders-search")
    public String showActivityLeadersByLastNameFirstNamePhoneEmail(Model out,
                                                                   @RequestParam String search) {

        out.addAttribute("activityLeaders", activityLeaderRepository.findByLastNameContainingOrFirstNameContainingOrEmailContaining(search, search, search));
        return "activity-leader-management";
    }

    @PostMapping("/activity-leader-creation")
    public String postForm(@ModelAttribute ActivityLeader activityLeader,
                           @RequestParam String unavailabilityStart,
                           @RequestParam String unavailabilityEnd) {

        String skillList = activityLeader.getSkillList();
        String[] skills = skillList.split(",");
        if (!skillList.equals("")) { // TODO voir pour ne pas recréer de skill dans la BDD
            for (String skill : skills) {
                Skill skillItem = new Skill(skill.trim());
                activityLeader.getSkills().add(skillItem);
            }
        }
        /*String valueList = activityLeader.getValueList();
        String[] values = valueList.split(",");
        if (!valueList.equals("")) {
            for (String value : values) {
                Value valueItem = new Value(value);
                activityLeader.getValues().add(valueItem);
            }
        }*/
        activityLeader = activityLeaderRepository.save(activityLeader);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = format.parse(unavailabilityStart);
            Date end = format.parse(unavailabilityEnd);
            Availability availability = new Availability(start,end, activityLeader);
            availability = availabilityRepository.save(availability);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "redirect:/activity-leader-modification/" + activityLeader.getId();
    }

    @GetMapping("/activity-leader-modification/{id}")
    public String getActivityLeaderModification(Model out,
                                                @PathVariable Long id) {

        Optional<ActivityLeader> optionalActivityLeader = activityLeaderRepository.findById(id);
        if (optionalActivityLeader.isPresent()) {
            ActivityLeader activityLeader = optionalActivityLeader.get();
            String skills = "";
            for (Skill skill : activityLeader.getSkills()) {
                skills += skill.getName() + ",";
            }
            if (skills.length() > 0) {
                skills = skills.substring(0, skills.length() - 1);
            }
            activityLeader.setSkillList(skills);
            out.addAttribute("activityLeader", activityLeader);
            out.addAttribute("valuesList", valueRepository.findAll());
            out.addAttribute("audiencesList", audienceRepository.findAll());
            out.addAttribute("diplomasList", diplomaRepository.findAll());
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

    @GetMapping("/activity-leader/disable")
    public String disableActivityLeader(@RequestParam Long id) {

        Optional<ActivityLeader> optionalActivityLeader = activityLeaderRepository.findById(id);
        if (optionalActivityLeader.isPresent()) {
            ActivityLeader activityLeader = optionalActivityLeader.get();
            activityLeader.setDisabled(true);
            activityLeaderRepository.save(activityLeader);
        }
        return "redirect:/activity-leader-management";
    }

    @GetMapping("/activity-leader-email/{id}")
    public String email(@PathVariable Long id){

        Optional<ActivityLeader> optionalActivityLeader = activityLeaderRepository.findById(id);
        if (optionalActivityLeader.isPresent()) {
            ActivityLeader activityLeader = optionalActivityLeader.get();
            emailService.sendNewActivityLeader(activityLeader);
        }
        return "redirect:/activity-leader-modification/" + id;
    }

    @PostMapping("/activity-leader-management-email")
    public String multiEmail(@RequestParam Long[] activityLeaders){

        for (Long id : activityLeaders){
            Optional<ActivityLeader> optionalActivityLeader = activityLeaderRepository.findById(id);
            if (optionalActivityLeader.isPresent()) {
                ActivityLeader activityLeader = optionalActivityLeader.get();
                emailService.sendInformationActivityLeader(activityLeader);
            }
        }
        return "redirect:/activity-leader-management";
    }
}