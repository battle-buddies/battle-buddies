package com.codeup.blog.blog.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String firstName;

    @Column(nullable = false, columnDefinition = "VARCHAR(80)")
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean married;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean milSpouse;

    @Column(nullable = false, columnDefinition = "VARCHAR(2000)")
    private String bio;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String branch;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String rank;

    @OneToOne
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "location_profile",
            joinColumns = {@JoinColumn(name="profile_id")},
            inverseJoinColumns = {@JoinColumn(name="location_id")}
    )
    private List<Location> location;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "profile_traits",
            joinColumns = {@JoinColumn(name="profile_id")},
            inverseJoinColumns = {@JoinColumn(name="trait_id")}
    )
    private List<Trait> traits;


    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Child> children;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Photo> photos;



    public Profile (){}

    public Profile(String firstName, String lastName, LocalDate birthDate, boolean married, boolean milSpouse, String bio, String branch, String rank, User user, List<Location> location, List<Trait> traits) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.married = married;
        this.milSpouse = milSpouse;
        this.bio = bio;
        this.branch = branch;
        this.rank = rank;
        this.user = user;
        this.location = location;
        this.traits = traits;
    }

    public Profile(long id, String firstName, String lastName, LocalDate birthDate, boolean married, boolean milSpouse, String bio, String branch, String rank, User user, List<Location> location, List<Trait> traits) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.married = married;
        this.milSpouse = milSpouse;
        this.bio = bio;
        this.branch = branch;
        this.rank = rank;
        this.user = user;
        this.location = location;
        this.traits = traits;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public boolean isMilSpouse() {
        return milSpouse;
    }

    public void setMilSpouse(boolean milSpouse) {
        this.milSpouse = milSpouse;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Child> getChildren (){
        return children;
    }

    public void setChildren() {
        this.children = children;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
