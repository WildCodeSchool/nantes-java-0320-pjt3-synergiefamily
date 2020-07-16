package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.ActivityLeader;
import com.wildcodeschool.synergieFamily.entity.Audience;
import com.wildcodeschool.synergieFamily.entity.Diploma;
import com.wildcodeschool.synergieFamily.entity.Value;
import com.wildcodeschool.synergieFamily.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.wildcodeschool.synergieFamily.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;

@Controller
public class FilterController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ActivityLeaderRepository activityLeaderRepository;
    @Autowired
    private AudienceRepository audienceRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private DiplomaRepository diplomaRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private ValueRepository valueRepository;

    @PostMapping("/filter")
    public String filter(Model model, @ModelAttribute ActivityLeader activityLeader){

        List<Diploma> diplomas = activityLeader.getDiplomas();
        Long[] diplomasIds;
        if (diplomas.size() > 0) {
            diplomasIds = new Long[diplomas.size()];
            for (int i = 0; i < diplomas.size(); i++) {
                Diploma diploma = diplomas.get(i);
                Long id = diploma.getId();
                diplomasIds[i] = id;
            }
        } else {
            diplomasIds = null;
        }

        List<Value> values = activityLeader.getValues();
        Long[] valuesIds;
        if (values.size() > 0) {
            valuesIds = new Long[values.size()];
            for (int i = 0; i < values.size(); i++) {
                Value value = values.get(i);
                Long id = value.getId();
                valuesIds[i] = id;
            }
        } else {
            valuesIds = null;
        }

        List<Audience> audiences = activityLeader.getAudiences();
        Long[] audiencesIds;
        if (audiences.size() > 0) {
            audiencesIds = new Long[audiences.size()];
            for (int i = 0; i < audiences.size(); i++) {
                Audience audience = audiences.get(i);
                Long id = audience.getId();
                audiencesIds[i] = id;
            }
        } else {
            audiencesIds = null;
        }

        List<ActivityLeader> list = activityLeaderRepository.findAllByFilter(activityLeader.getFirstName(),
                activityLeader.getLastName(),
                activityLeader.getEmail(),
                activityLeader.getPhone(),
                activityLeader.getLocation().getAddress1(),
                activityLeader.getLocation().getAddress2(),
                activityLeader.getLocation().getCity(),
                activityLeader.getLocation().getPostcode(),
                activityLeader.getExperience(),
                diplomasIds,
                valuesIds,
                audiencesIds
                );

        //TODO  activityLeader.hasACar()
        model.addAttribute("activityleaders", list);
        return "filter";
    }

    @GetMapping("/filter")
    public String showFilter(Model model){
        model.addAttribute("activityLeader", new ActivityLeader());
        model.addAttribute("valuesList", valueRepository.findAll());
        model.addAttribute("audiencesList", audienceRepository.findAll());
        model.addAttribute("diplomasList", diplomaRepository.findAll());
        return "filter";
    }

    @Autowired
    private JavaMailSender javaMailSender;

    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("alan.raoul22@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }

    @GetMapping("/email")
    @ResponseBody
    public String email(){
        sendEmail();
        return "ok";
    }
}

