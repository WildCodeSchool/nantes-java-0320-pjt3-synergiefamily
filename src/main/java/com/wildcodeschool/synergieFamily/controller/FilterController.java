package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.ActivityLeader;
import com.wildcodeschool.synergieFamily.entity.Diploma;
import com.wildcodeschool.synergieFamily.entity.Value;
import com.wildcodeschool.synergieFamily.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Filter;

@Controller
public class FilterController {

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

    @GetMapping("/tacos")
    @ResponseBody
    public String filter(@ModelAttribute ActivityLeader activityLeader){
        List<ActivityLeader> list = activityLeaderRepository.findAllByFilter(activityLeader.getFirstName(),"tacos");
        return "ok";
    }
}
