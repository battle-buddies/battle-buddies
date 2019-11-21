package com.codeup.blog.blog.controllers;


import com.codeup.blog.blog.models.*;

import com.codeup.blog.blog.repositories.*;

import com.codeup.blog.blog.models.Relationship;
import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.LocationRepository;
import com.codeup.blog.blog.repositories.ProfileRepository;
import com.codeup.blog.blog.repositories.RelationshipRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import com.codeup.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    private ProfileRepository profileDao;
    private UserRepository userDao;
    private RelationshipRepository relationshipDao;
    private HobbyRepository hobbyDao;
    private TraitRepository traitDao;
    private LocationRepository locationDao;
    private BranchRepository branchDao;
    private RankRepository rankDao;




    @Autowired
    private UserService usersService;


    public ProfileController(ProfileRepository profileDao, UserRepository userDao, RelationshipRepository relationshipDao, HobbyRepository hobbyDao, TraitRepository traitDao, LocationRepository locationDao, BranchRepository branchDao, RankRepository rankDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
        this.relationshipDao = relationshipDao;
        this.hobbyDao = hobbyDao;
        this.traitDao = traitDao;
        this.locationDao = locationDao;
        this.branchDao = branchDao;
        this.rankDao = rankDao;
    }

    @GetMapping("/users/profile/{id}")
    public String showUserProfile(Model vModel, @PathVariable long id){

        vModel.addAttribute("user", userDao.getOne(id));



//        FRIEND REQUESTS
        List<User> allUsers = userDao.findAll();
        List<User> pendingFriends = new ArrayList<>();
        for (User user: allUsers) {

            for (Relationship relationship : user.getRelationships()) {

                if(relationship.getFriend().getId() == id){
                    pendingFriends.add(user);
                }
            }
        }

        vModel.addAttribute("pendingFriends", pendingFriends);


        return "users/profile";
    }




    @GetMapping("/users/userdetails")
    public String showUserDetails(Model model){
        model.addAttribute("profile", new Profile());
        model.addAttribute("hobbies", hobbyDao.findAll());
        model.addAttribute("traits", traitDao.findAll());
        model.addAttribute("branches", branchDao.findAll());
        model.addAttribute("ranks", rankDao.findAll());


        return "users/userdetails";
    }

    @PostMapping("/users/userdetails")
    public String submitUserDetails
            (@ModelAttribute Profile profileDetails,
             @RequestParam(value="traits", required = false)ArrayList<Trait> traits,
             @RequestParam(value="hobbies", required = false)ArrayList<Hobby> hobbies,
             @RequestParam(value="branch", required = false) Branch branch,
             @RequestParam(value="rank", required = false) Rank rank){
        User user = usersService.loggedInUser();
        Profile person = new Profile();
        person.setUser(user);
        person.setTraits(traits);
        person.setHobbies(hobbies);
        person.setBranch(branch);
        person.setRank(rank);

        person.setBio("jshdkjhdkjdhk");
        person.setGender("female");
        List<Child> children = new ArrayList<>();
        children.add(new Child("female", 6));
        person.setChildren(children);
        person.setFirstName("dani");
        person.setLastName("lame");
        person.setMarried(true);
        person.setMilSpouse(false);
        person.setage(45);

        profileDao.save(person);


        return "redirect:/users/profile/" + user.getId();
    }


    @GetMapping("/users/{id}/friend-request")
    public String sendFriendRequest(@PathVariable long id, Model vModel) {
        User userOne = usersService.loggedInUser();
        User requestedFriend = userDao.getOne(id);
        FriendStatus status = FriendStatus.PENDING;
        Relationship relationship = new Relationship(userOne, requestedFriend, status);
        relationshipDao.save(relationship);


        return "redirect:/users/profile/"+id;
    }

    @GetMapping("/users/{id}/accept-request")
    public String acceptFriendRequest(@PathVariable long id ) {
        User userOne = usersService.loggedInUser();
        Relationship acceptFriend = relationshipDao.getOne(id);
        FriendStatus status = FriendStatus.ACCEPTED;
        User requestedFriend = acceptFriend.getUser();
        acceptFriend.setStatus(status);


        Relationship relationshipOne = new Relationship(userOne, requestedFriend, status);
        relationshipDao.save(relationshipOne);
        relationshipDao.save(acceptFriend);



        return "redirect:/users/profile/"+ userOne.getId();
    }



    @GetMapping("/users/location")

    public String showCreateForm(Model model) {
        model.addAttribute("location", new Location());

        return "users/location";
    }

    @PostMapping("/users/location")
    public String create(@ModelAttribute Location locationToBeCreated){
//        locationToBeCreated.setProfiles(profileDao.getOne(1));
        Location savedLocation = locationDao.save(locationToBeCreated);
        return "redirect:/users/location" + savedLocation.getId();
    }


}
