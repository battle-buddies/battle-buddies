package com.codeup.blog.blog.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "profiles")
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

    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private String gender;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean married;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean milSpouse;

    @Column(nullable = false, columnDefinition = "VARCHAR(2000)")
    private String bio;


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
            name = "profile_trait",
            joinColumns = {@JoinColumn(name="profile_id")},
            inverseJoinColumns = {@JoinColumn(name="trait_id")}
    )
    private List<Trait> traits;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hobby_profile",
            joinColumns = {@JoinColumn(name="profile_id")},
            inverseJoinColumns = {@JoinColumn(name="hobby_id")}
    )
    private List<Hobby> hobbies;


    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Child> children;


    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Photo> photos;


    @ManyToOne
    @JoinColumn (name = "branch_id")
    private Branch branch;


    @ManyToOne
    @JoinColumn (name = "rank_id")
    private Rank rank;



    public Profile (){}

    public Profile(long id,String firstName, String lastName, LocalDate birthDate, String gender, boolean married, boolean milSpouse, String bio, User user, List<Location> location, List<Trait> traits, List<Hobby> hobbies, List<Child> children, List<Photo> photos, Branch branch, Rank rank) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.married = married;
        this.milSpouse = milSpouse;
        this.bio = bio;
        this.user = user;
        this.location = location;
        this.traits = traits;
        this.hobbies = hobbies;
        this.children = children;
        this.photos = photos;
        this.branch = branch;
        this.rank = rank;
    }

    public Profile(String firstName, String lastName, LocalDate birthDate, String gender, boolean married, boolean milSpouse, String bio, User user, List<Location> location, List<Trait> traits, List<Hobby> hobbies, List<Child> children, List<Photo> photos, Branch branch, Rank rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.married = married;
        this.milSpouse = milSpouse;
        this.bio = bio;
        this.user = user;
        this.location = location;
        this.traits = traits;
        this.hobbies = hobbies;
        this.children = children;
        this.photos = photos;
        this.branch = branch;
        this.rank = rank;
    }

    public String getGender() {
        return gender;
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

    public void setGender(String gender) {
        this.gender = gender;
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



    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
