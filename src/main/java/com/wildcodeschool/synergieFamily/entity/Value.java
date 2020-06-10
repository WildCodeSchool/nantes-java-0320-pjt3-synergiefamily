package com.wildcodeschool.synergieFamily.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "value")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "values")
    private List<ActivityLeader> activityLeaders = new ArrayList<>();

    public Value() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
