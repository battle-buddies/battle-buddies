package com.codeup.blog.blog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Relationship")
public class Relationship {

    @Column(nullable = false, unique = true)
    private long userOneId;

    @Column(nullable = false, unique = true)
    private long userTwoId;

    @Column(nullable = false)
    private int status;

    @ManyToOne
    private User user;


    public Relationship(long userOneId, long userTwoId, int status) {
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
        this.status = status;
    }

    public Relationship() {}

    public long getUserOneId() {
        return userOneId;
    }

    public void setUserOneId(long userOneId) {
        this.userOneId = userOneId;
    }

    public long getUserTwoId() {
        return userTwoId;
    }

    public void setUserTwoId(long userTwoId) {
        this.userTwoId = userTwoId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
