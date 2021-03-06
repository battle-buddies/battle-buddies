package com.codeup.blog.blog.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name= "meetups")
public class MeetUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT(2000)")
    private String description;

    @Column(nullable = false, columnDefinition = "VARCHAR(250)")
    private String address;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String date;

    @Column
    private int count;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "meetup_users",
            joinColumns = {@JoinColumn(name="meetup_id")},
            inverseJoinColumns = {@JoinColumn(name="users_id")}
    )
    private List<User> interestedUsers;


    @ManyToOne
    @JoinColumn (name = "location_id")
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meetUp")
    private List<Comment> comments;

    public MeetUp(){}

    public MeetUp(String title, String description, String address, User user, Location location, String date, int count, List<User> interestedUsers, List<Comment> comments) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.user = user;
        this.location = location;
        this.date = date;
        this.count = count;
        this.interestedUsers = interestedUsers;
        this.comments = comments;
    }
    public MeetUp(long id, String title, String description, String address, User user, Location location, String date, int count,List<User> interestedUsers ) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.user = user;
        this.location = location;
        this.date = date;
        this.count = count;
        this.interestedUsers = interestedUsers;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<User> getInterestedUsers() {
        return interestedUsers;
    }

    public void setInterestedUsers(List<User> users) {
        this.interestedUsers = users;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
