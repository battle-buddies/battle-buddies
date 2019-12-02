package com.codeup.blog.blog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, columnDefinition = "VARCHAR(80)", unique = true)
    private String username;
    @Column(nullable = false, columnDefinition = "VARCHAR(200)", unique = true)
    private String email;
    @Column(nullable = false, columnDefinition = "VARCHAR(250)")
    private String password;

//    @Column(nullable = false, columnDefinition = "TINYINT(1)")
//    private boolean isVerified;

    @Column( columnDefinition = "TINYINT(1)")
    private boolean isAdmin;

    @ManyToMany(mappedBy = "user")
    private List<MeetUp> meetUps;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<MeetUp> interestedInMeetUps;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Relationship> relationships;

    @OneToOne
    private Profile profile;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments;



    public User(){

    }

    public User(String username){
        this.username = username;
    }


    public User(String username, String email, String password, boolean isAdmin, List<MeetUp> meetUps, List<Relationship> relationships, Profile profile, List<MeetUp> interestedInMeetUps) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.meetUps = meetUps;
        this.relationships = relationships;
        this.profile = profile;
        this.interestedInMeetUps = interestedInMeetUps;
    }

    // Copy constructor an alternative for clone
    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.email = user.email;
        this.profile = user.profile;
    }

    public User(String username, String email, String password, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(long id, String username, String email, String password, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public List<MeetUp> getMeetUps() {
        return meetUps;
    }

    public void setMeetUps(List<MeetUp> meetUps) {
        this.meetUps = meetUps;
    }
    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<MeetUp> getInterestedInMeetUps() {
        return interestedInMeetUps;
    }

    public void setInterestedInMeetUps(List<MeetUp> interestedInMeetUps) {
        this.interestedInMeetUps = interestedInMeetUps;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
