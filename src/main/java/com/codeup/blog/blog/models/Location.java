package com.codeup.blog.blog.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(80)")
    private String city;

    @Column( columnDefinition = "VARCHAR(2)")
    private String state;

    @Column(nullable = false, columnDefinition = "VARCHAR(80)")
    private String country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<MeetUp> meetUps;

    @ManyToMany(mappedBy = "locations")
    private List<Profile> profiles;

}
