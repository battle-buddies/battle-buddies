package com.codeup.blog.blog.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(80)")
    private String city;

    @Column( columnDefinition = "VARCHAR(2)")
    private String state;

    @Column(nullable = false, columnDefinition = "VARCHAR(80)")
    private String country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<MeetUp> meetUps;

    @ManyToMany(mappedBy = "location")
    private List<Profile> profiles;

    public Location(String city, String state, String country, List<MeetUp> meetUps, List<Profile> profiles) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.meetUps = meetUps;
        this.profiles = profiles;
    }
    public Location(long id, String city, String state, String country, List<MeetUp> meetUps, List<Profile> profiles) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.meetUps = meetUps;
        this.profiles = profiles;
    }

    public Location (){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<MeetUp> getMeetUps() {
        return meetUps;
    }

    public void setMeetUps(List<MeetUp> meetUps) {
        this.meetUps = meetUps;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }


}
