package com.wildcodeschool.synergieFamily.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public static String randomPassword(int length) {

        String password = "";
        for (int i = 0; i < length - 2; i++) {
            password = password + randomCharacter("abcdefghijklmnopqrstuvwxyz");
        }
        String randomDigit = randomCharacter(("0123456789"));
        password = insertStringAtRandomPosition(password, randomDigit);
        String randomSymbol = randomCharacter("+-*/?!@#$%&");
        password = insertStringAtRandomPosition(password, randomSymbol);
        return password;

    }

    public static String randomCharacter(String characters) {
        int length = characters.length();
        int randomIndex = (int) (length * Math.random());
        return characters.substring(randomIndex, randomIndex + 1);
    }

    public static String insertStringAtRandomPosition(String stringDestination, String stringToInsert) {

        int length = stringDestination.length();
        int randomIndex = (int) ((length + 1) *  Math.random());
        return stringDestination.substring(0, randomIndex) + stringToInsert + stringDestination.substring(randomIndex);

    }
}
