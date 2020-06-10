package com.wildcodeschool.synergieFamily.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import javax.persistence.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "activity_leader")
public class ActivityLeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = true)
    private String phone;

    @NotNull
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String birthdate;

    @Column(nullable = false)
    private boolean hasACar;

    @Column(nullable = false)
    private String experience;

    @Column(nullable = false)
    private String avaibility;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @ManyToMany
    @JoinTable(name = "activity_leader_skill",
            joinColumns = @JoinColumn(name = "activity_leader_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills = new ArrayList<>();



    private boolean isActive;

    private boolean isDraft;

    public ActivityLeader() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isHasACar() {
        return hasACar;
    }

    public void setHasACar(boolean hasACar) {
        this.hasACar = hasACar;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAvaibility() {
        return avaibility;
    }

    public void setAvaibility(String avaibility) {
        this.avaibility = avaibility;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }
}
