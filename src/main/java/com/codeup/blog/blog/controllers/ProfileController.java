package com.codeup.blog.blog.controllers;


import com.codeup.blog.blog.models.FriendStatus;

import com.codeup.blog.blog.models.Profile;
import com.codeup.blog.blog.models.Relationship;
import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.*;

import com.codeup.blog.blog.models.Location;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.PrivateKey;
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



    @Autowired
    private UserService usersService;


    public ProfileController(ProfileRepository profileDao, UserRepository userDao, RelationshipRepository relationshipDao, HobbyRepository hobbyDao, TraitRepository traitDao, LocationRepository locationDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
        this.relationshipDao = relationshipDao;
        this.hobbyDao = hobbyDao;
        this.traitDao = traitDao;
        this.locationDao = locationDao;
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

        return "users/userdetails";
    }

    @PostMapping("/users/userdetails")
    public String submitUserDetails(@ModelAttribute Profile profileDetails){
        User user = usersService.loggedInUser();
         profileDao.save(profileDetails);

        return "users/profile/" + user.getId();
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
