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

    public Comment(String comment, MeetUp meetUp) {
        this.comment = comment;
        this.meetUp = meetUp;
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
}
