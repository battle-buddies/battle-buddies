package com.codeup.blog.blog.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "text", unique=true)
    private String location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<MeetUp> meetUps;

    @ManyToMany(mappedBy = "location")
    private List<Profile> profiles;

    public Location() {
    }

    public Location(String location) {
        this.location = location;
    }

    public Location(long id, String location, List<MeetUp> meetUps, List<Profile> profiles) {
        this.id = id;
        this.location = location;
        this.meetUps = meetUps;
        this.profiles = profiles;
    }

    public Location(String location, List<MeetUp> meetUps, List<Profile> profiles) {
        this.location = location;
        this.meetUps = meetUps;
        this.profiles = profiles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
