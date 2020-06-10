package com.wildcodeschool.synergieFamily.entity;

import com.sun.istack.NotNull;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String address1;

    @Column(nullable = true)
    private String address2;

    @NotNull
    @Column(nullable = false)
    private int postcode;

    @NotNull
    @Column(nullable = false)
    private String city;

    public Location() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String adress2) {
        this.address2 = adress2;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}