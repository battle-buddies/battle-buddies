package com.codeup.blog.blog.models;

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

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isAdmin;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Relationship> relationships;


    public User(User user){

    }

    public User(String username, String email, String password, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(long id, String username, String email, String password, boolean isAdmin) {
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

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    public List<Relationship> getRelationships (){
        return relationships;
    }


}
