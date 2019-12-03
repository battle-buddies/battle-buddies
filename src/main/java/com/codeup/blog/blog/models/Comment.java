package com.codeup.blog.blog.models;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT(2000)")
    private String comment;

    @ManyToOne
    @JoinColumn (name = "meet_up_id")
    private MeetUp meetUp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "profile_id")
    private Profile profile;

    public Comment(String comment, MeetUp meetUp, User user) {
        this.comment = comment;
        this.meetUp = meetUp;
        this.user = user;
    }

    public Comment(String comment, Profile profile, User user) {
        this.comment = comment;
        this.profile = profile;
        this.user = user;
    }


    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MeetUp getMeetUp() {
        return meetUp;
    }

    public void setMeetUp(MeetUp meetUp) {
        this.meetUp = meetUp;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Profile getProfile() { return profile; }

    public void setProfile(Profile profile) { this.profile = profile; }
}
