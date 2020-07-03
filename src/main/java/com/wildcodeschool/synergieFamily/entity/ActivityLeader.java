package com.wildcodeschool.synergieFamily.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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

    @Column
    private String phone;

    @NotNull
    @Column(nullable = false)
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    @Column
    private Boolean hasACar;

    @Column
    private String experience;

    @Column
    private String availability;

    @Column
    private String comment;

    @Column
    private Boolean disabled;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location = new Location();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "skill_activity_leader",
            joinColumns = @JoinColumn(name = "activity_leader_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "value_activity_leader",
            joinColumns = @JoinColumn(name = "activity_leader_id"),
            inverseJoinColumns = @JoinColumn(name = "value_id"))
    private List<Value> values = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "diploma_activity_leader",
            joinColumns = @JoinColumn(name = "activity_leader_id"),
            inverseJoinColumns = @JoinColumn(name = "diploma_id"))
    private List<Diploma> diplomas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "audience_activity_leader",
            joinColumns = @JoinColumn(name = "activity_leader_id"),
            inverseJoinColumns = @JoinColumn(name = "audience_id"))
    private List<Audience> audiences = new ArrayList<>();

    @Transient
    private Boolean active;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean draft = true;

    @Transient
    private String skillList;

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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Boolean getHasACar() {
        return hasACar;
    }

    public void setHasACar(Boolean hasACar) {
        this.hasACar = hasACar;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
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

    public Boolean getDraft() {

        return draft;
    }  

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public List<Diploma> getDiplomas() {
        return diplomas;
    }

    public void setDiplomas(List<Diploma> diplomas) {
        this.diplomas = diplomas;
    }

    public List<Audience> getAudiences() {
        return audiences;
    }

    public void setAudiences(List<Audience> audiences) {
        this.audiences = audiences;
    }

    public Boolean getActive() {
        if (this.getStartDate() == null || this.getStartDate().compareTo(new Date()) > 0
                || this.getEndDate() == null || this.getEndDate().compareTo(new Date()) < 0 ) {
            return false;
        }
        return true;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSkillList() {
        return skillList;
    }

    public void setSkillList(String skillList) {
        this.skillList = skillList;
    }

    public int getAge() {

        if (this.birthdate != null) {
            return Period.between(convertDate(this.birthdate), convertDate(new Date())).getYears();
        } else {
            return 0;
        }
    }

    public LocalDate convertDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
