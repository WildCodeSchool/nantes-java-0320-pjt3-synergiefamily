package com.wildcodeschool.synergieFamily.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "audiences")
public class Audience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ageBracket;

    @ManyToMany(mappedBy = "audiences")
    private List<ActivityLeader> activityLeaders = new ArrayList<>();

    public Audience(Long id, String ageBracket) {
        this.id = id;
        this.ageBracket = ageBracket;
    }

    public Audience() {
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgeBracket() {
        return ageBracket;
    }

    public void setAgeBracket(String ageBracket) {
        this.ageBracket = ageBracket;
    }

    public List<ActivityLeader> getActivityLeaders() {
        return activityLeaders;
    }

    public void setActivityLeaders(List<ActivityLeader> activityLeaders) {
        this.activityLeaders = activityLeaders;
    }
}
