package com.wildcodeschool.synergieFamily.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "value")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "values")
    private List<ActivityLeader> activityLeaders = new ArrayList<>();

    @Transient
    private String valueList;

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

    /*public Value(String valueList) {
        this.valueList = valueList;
    }

    public String getValueList() {
        return valueList;
    }

    public void setValueList(String valueList) {
        this.valueList = valueList;
    }*/
}
