package com.codeup.blog.blog.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ranks")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(80)")
    private String rank;

    @OneToMany(mappedBy = "rank", cascade = CascadeType.ALL)
    private List<Profile> profiles;


    public Rank() {
    }

    public Rank(String rank, List<Profile> profiles) {
        this.rank = rank;
        this.profiles = profiles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }


}
