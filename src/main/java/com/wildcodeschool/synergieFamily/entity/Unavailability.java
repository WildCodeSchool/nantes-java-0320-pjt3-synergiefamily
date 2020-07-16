package com.wildcodeschool.synergieFamily.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "unavailability")
public class Unavailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "activity_leader_id")
    private ActivityLeader activityLeader;

    public Unavailability() {
    }

    public Unavailability(Date startDate, Date endDate, ActivityLeader activityLeader) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.activityLeader = activityLeader;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ActivityLeader getActivityLeader() {
        return activityLeader;
    }

    public void setActivityLeader(ActivityLeader activityLeader) {
        this.activityLeader = activityLeader;
    }
}
