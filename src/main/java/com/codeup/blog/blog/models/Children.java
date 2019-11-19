package com.codeup.blog.blog.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "children")
public class Children {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private int gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Children(int gender, LocalDate birthDate) {
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Children(long id, int gender, LocalDate birthDate) {
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Children(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
