package com.codeup.blog.blog.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private String gender;

    @Column(nullable = false)
    private int age;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Child(String gender, int age) {
        this.gender = gender;
        this.age = age;
    }

    public Child(long id, String gender, int age) {

        this.id = id;
        this.gender = gender;
        this.age = age;
    }

    public Child(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
