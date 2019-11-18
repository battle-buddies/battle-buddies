package com.codeup.blog.blog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="traits")
public class Trait {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(80)")
    private String trait;

    @ManyToMany(mappedBy = "tags")
    private List<Profile> profiles;

    public Trait(){}

    public Trait(String trait, List<Profile> profiles) {
        this.trait = trait;
        this.profiles = profiles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}
