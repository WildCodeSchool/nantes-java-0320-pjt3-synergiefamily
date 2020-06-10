package com.wildcodeschool.synergieFamily.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diplomas")
public class Diploma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "diplomas")
    private List<ActivityLeader> activityLeaders = new ArrayList<>();

    public Diploma(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Diploma() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ActivityLeader> getActivityLeaders() {
        return activityLeaders;
    }

    public void setActivityLeaders(List<ActivityLeader> activityLeaders) {
        this.activityLeaders = activityLeaders;
    }
}
