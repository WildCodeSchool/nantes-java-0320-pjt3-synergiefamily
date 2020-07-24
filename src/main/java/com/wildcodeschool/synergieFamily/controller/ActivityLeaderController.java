package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.*;
import com.wildcodeschool.synergieFamily.repository.*;
import com.wildcodeschool.synergieFamily.service.EmailService;
import com.wildcodeschool.synergieFamily.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ActivityLeaderController {

    @Autowired
    private UnavailabilityRepository unavailabilityRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DiplomaRepository diplomaRepository;

    @Autowired
    private AudienceRepository audienceRepository;

    @Autowired
    private ValueRepository valueRepository;
//Article article = new Article();
        //out.addAttribute("newArticle", article);
    @Autowired
    private TokenService tokenService;

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
        out.addAttribute("external", false);

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
                           @RequestParam(required = false) String unavailabilityStart,
                           @RequestParam(required = false) String unavailabilityEnd) {

        String skillList = activityLeader.getSkillList();
        String[] skills = skillList.split(",");
        if (!skillList.equals("")) {

            for (String skill : skills) {

                Skill skillItem = new Skill(skill.trim());
                activityLeader.getSkills().add(skillItem);
            }
        }

        if (activityLeader.getId() != null) {

            unavailabilityRepository.deleteAllByActivityLeader(activityLeader);
            for (Unavailability unavailability : activityLeader.getUnavailabilities()) {

                unavailability.setActivityLeader(activityLeader);
                unavailabilityRepository.save(unavailability);
            }
        }
        activityLeader = activityLeaderRepository.save(activityLeader);

        if (unavailabilityStart != null && !unavailabilityStart.isEmpty()
                && unavailabilityEnd != null && !unavailabilityEnd.isEmpty()
        ) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {

                Date start = format.parse(unavailabilityStart);
                Date end = format.parse(unavailabilityEnd);
                Unavailability unavailability = new Unavailability(start, end, activityLeader);
                unavailabilityRepository.save(unavailability);
            } catch (ParseException e) {

                e.printStackTrace();
            }
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
            out.addAttribute("external", false);
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
            out.addAttribute("valuesList", valueRepository.findAll());
            out.addAttribute("audiencesList", audienceRepository.findAll());
            out.addAttribute("diplomasList", diplomaRepository.findAll());
            out.addAttribute("editable", false);
            out.addAttribute("external", false);
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
    public String email(HttpServletRequest request,  @PathVariable Long id) {

        String randomToken = tokenService.randomToken(16, 4);
        Optional<ActivityLeader> optionalActivityLeader = activityLeaderRepository.findById(id);
        if (optionalActivityLeader.isPresent()) {

            ActivityLeader activityLeader = optionalActivityLeader.get();
            activityLeader.setToken(randomToken);
            activityLeader = activityLeaderRepository.save(activityLeader);
            String url = request.getRequestURL().substring(0, request.getRequestURL().length()
                    - request.getRequestURI().length()) + request.getContextPath();
            emailService.sendNewActivityLeader(url, activityLeader, randomToken);
        }
        return "redirect:/activity-leader-modification/" + id;
    }

    @GetMapping("/activity-leader-edit/{token}")
    public String getActivityLeaderEdit(Model out,
                                        @PathVariable String token) {

        Optional<ActivityLeader> optionalActivityLeader = activityLeaderRepository.findByToken(token);
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
            out.addAttribute("external", true);
        }
        return "activity-leader-creation";
    }

    @PostMapping("/activity-leader-edit/")
    @ResponseBody
    public String editForm(@ModelAttribute ActivityLeader activityLeader,
                           @RequestParam(required = false) String unavailabilityStart,
                           @RequestParam(required = false) String unavailabilityEnd) {

        postForm(activityLeader, unavailabilityStart, unavailabilityEnd);
        activityLeader.setDraft(false);
        activityLeaderRepository.save(activityLeader);
        return "Votre fiche a bien été modifiée, merci.";
    }

    @PostMapping("/activity-leader-management-email")
    public String multiEmail(
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) List<Long> activityLeaders) {

        if (activityLeaders != null) {

            for (Long id : activityLeaders) {

                Optional<ActivityLeader> optionalActivityLeader = activityLeaderRepository.findById(id);
                if (optionalActivityLeader.isPresent()) {

                    ActivityLeader activityLeader = optionalActivityLeader.get();
                    emailService.sendInformationActivityLeader(activityLeader, subject, content);
                }
            }
        }
        return "redirect:/activity-leader-management";
    }

    @PostMapping("/activity-leader-filter-email")
    public String filterEmail(
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) List<Long> activityLeaders) {

        multiEmail(subject, content, activityLeaders);
        return "redirect:/filter";
    }
}