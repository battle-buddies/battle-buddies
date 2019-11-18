//package com.codeup.blog.blog.models;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "Relationship")
//public class Relationship {
//
//    @Column(nullable = false, unique = true)
//    private long userOneId;
//
//    @Column(nullable = false, unique = true)
//    private long userTwoId;
//
//    @Column(nullable = false)
//    private int status;
//
//    @ManyToOne
//    @JoinColumn(name = "id")
//    private User userone;
//
//    @ManyToOne
//    @JoinColumn(name = "id")
//    private User usertwo;
//
//
//    public Relationship(long userOneId, long userTwoId, int status) {
//        this.userOneId = userOneId;
//        this.userTwoId = userTwoId;
//        this.status = status;
//    }
//
//    public Relationship() {}
//
//    public long getUserOneId() {
//        return userOneId;
//    }
//
//    public void setUserOneId(long userOneId) {
//        this.userOneId = userOneId;
//    }
//
//    public long getUserTwoId() {
//        return userTwoId;
//    }
//
//    public void setUserTwoId(long userTwoId) {
//        this.userTwoId = userTwoId;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public User getUserOne() {
//        return userone;
//    }
//
//    public void setUserOne(User userone) {
//        this.userone = userone;
//    }
//
//    public User getUserTwo() {
//        return usertwo;
//    }
//
//    public void setUserTwo(User usertwo) {
//        this.usertwo = usertwo;
//    }
//}
