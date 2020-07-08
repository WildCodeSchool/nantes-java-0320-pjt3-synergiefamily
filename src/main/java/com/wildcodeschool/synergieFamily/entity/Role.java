package com.wildcodeschool.synergieFamily.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    @Column
    private String nameForDisplay;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
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
        this.nameForDisplay = transformRoleForDisplay(name);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getNameForDisplay() {
        return nameForDisplay;
    }

    public void setNameForDisplay(String nameForDisplay) {
        this.nameForDisplay = nameForDisplay;
    }

    public static String transformRoleForDisplay(String roleName) {

        int length = roleName.length();
        String roleNameForDisplay = roleName.substring(4,length); // "ROLE_" deletion
        return roleNameForDisplay;
    }


}