package com.codeup.blog.blog.controllers;
import com.codeup.blog.blog.models.*;
import com.codeup.blog.blog.repositories.*;
import com.codeup.blog.blog.models.Relationship;
import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.models.Location;
import com.codeup.blog.blog.repositories.LocationRepository;
import com.codeup.blog.blog.repositories.ProfileRepository;
import com.codeup.blog.blog.repositories.RelationshipRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import com.codeup.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
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
    private PhotoRepository photoDao;
    private CommentRepository commentDao;
    private ChildRepository childDao;


    @Value("${file-upload-path}")
    private String uploadPath;




    @Autowired
    private UserService usersService;
    @Autowired
    UserService usersSvc;


    public ProfileController(ProfileRepository profileDao, UserRepository userDao, RelationshipRepository relationshipDao, HobbyRepository hobbyDao, TraitRepository traitDao, LocationRepository locationDao, BranchRepository branchDao, RankRepository rankDao,PhotoRepository  photoDao, CommentRepository commentDao, ChildRepository childDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
        this.relationshipDao = relationshipDao;
        this.hobbyDao = hobbyDao;
        this.traitDao = traitDao;
        this.locationDao = locationDao;
        this.branchDao = branchDao;
        this.rankDao = rankDao;
        this.photoDao  = photoDao;
        this.commentDao = commentDao;
        this.childDao = childDao;
    }

    @GetMapping("/users/profile/{id}")
    public String showUserProfile(Model vModel, @PathVariable long id){
        User loggedInUser = usersService.loggedInUser();
        Profile loggedInProfile = loggedInUser.getProfile();
        if (loggedInProfile == null ||loggedInProfile.getFirstName() == null || loggedInProfile.getLastName() == null){
            return "redirect:/users/userdetails";
        }

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

//        SUGGESTED FRIENDS
        List<User> suggestedFriends = new ArrayList<>();
        for (Profile profile : profileDao.findAll()) {
            int total = 0;

            // add logic to weed out accepted friends
            if (loggedInProfile != profile){
                if(loggedInProfile.getLocation().equals(profile.getLocation())){
                    total += 40;
                }

                for (Trait trait : profile.getTraits()) {
                    if (loggedInProfile.getTraits().contains(trait)){
                        total += 5;
                    }
                }

                for (Hobby hobby : profile.getHobbies()) {
                    if (loggedInProfile.getHobbies().contains(hobby)){
                        total += 5;
                    }
                }

                if (loggedInProfile.isMarried() == profile.isMarried()){
                    total += 10;
                }

                if(loggedInProfile.getGender() == profile.getGender()){
                    total += 10;
                }

                if(loggedInProfile.getBranch().getBranch() == profile.getBranch().getBranch()){
                    total += 20;
                }

                if(loggedInProfile.isMilSpouse() == profile.isMilSpouse()) {
                    total += 10;
                }

                if (profile.getage() >= loggedInProfile.getage() - 3 && profile.getage() <= loggedInProfile.getage() + 3){
                    total += 20;
                }

                if(loggedInProfile.getRank().getRank().charAt(0) == profile.getRank().getRank().charAt(0) ){
                    total += 10;
                }else {
                    total -=200;
                }

                if (total >= 100){
                    suggestedFriends.add(profile.getUser());
                }


            }
        }
        vModel.addAttribute("suggestedFriends", suggestedFriends);


        List<Comment> comments = new ArrayList<>();
        for (Comment comment: commentDao.findAll()) {
            if (comment.getProfile() != null && comment.getProfile().getId() == id){
                comments.add(comment);
            }
        }
        vModel.addAttribute("comments", comments);

        return "users/profile";
    }




    @GetMapping("/users/userdetails")
    public String showUserDetails(Model model){
        model.addAttribute("profile", new Profile());
        model.addAttribute("hobbies", hobbyDao.findAll());
        model.addAttribute("traits", traitDao.findAll());
        model.addAttribute("branches", branchDao.findAll());
        model.addAttribute("ranks", rankDao.findAll());
        model.addAttribute("locations", locationDao.findAll());
        model.addAttribute("location", new Location());
        model.addAttribute("children", childDao.findAll());

        return "users/userdetails";
    }

    @PostMapping("/users/userdetails")
    public String submitUserDetails(
             @Valid Profile profile,
             @RequestParam(name="traits", required = false)ArrayList<Long> traitIds,
             @RequestParam(name="hobbies", required = false)ArrayList<Long> hobbyIds,
             @RequestParam(name="children", required = false)ArrayList<Long> childIds,
             @RequestParam(name="branch", required = false)Long branchId,
             @RequestParam(name="rank", required = false) Long rankId,
             Model m,
             @RequestParam(name = "file") MultipartFile uploadedFile,
             @RequestParam(name = "form2", required = false) Location locationToBeCreated


    ){
        User user = usersService.loggedInUser();
       profile.setUser(user);
        profile.setBranch(branchDao.getOne(branchId));
        profile.setRank(rankDao.getOne(rankId));
//        locationDao.save(locationToBeCreated);




        // FINDS THE Hobbies THAT WERE SELECTED BY USER
        List<Hobby> hobbiesToAdd = new ArrayList<>();
        for (long hobbyId : hobbyIds) {
            for (Hobby all : hobbyDao.findAll()) {
                if (hobbyId == all.getId()) {
                    hobbiesToAdd.add(hobbyDao.getOne(hobbyId));
                }
            }
            // ADDS EACH INDIVIDUAL Hobby TO PROFILE
            if (hobbiesToAdd != null) {
                profile.setHobbies(new ArrayList<>());
                for (Hobby hobby : hobbiesToAdd) {
                    profile.getHobbies().add(hobby);
                }
            }


            // FINDS THE TRAITS THAT WERE SELECTED BY USER
            List<Trait> traitsToAdd = new ArrayList<>();
            for (long traitId : traitIds) {
                for (Trait all : traitDao.findAll()) {
                    if (traitId == all.getId()) {
                        traitsToAdd.add(traitDao.getOne(traitId));
                    }
                }
            }

            // ADDS EACH INDIVIDUAL TRAIT TO PROFILE
            if (traitsToAdd != null) {
                profile.setTraits(new ArrayList<>());
                for (Trait trait : traitsToAdd) {
                    profile.getTraits().add(trait);
                }
            }

            // FINDS THE Children THAT WERE SELECTED BY USER
            List<Child> childToAdd = new ArrayList<>();
            for (long childId : childIds) {
                for (Child all: childDao.findAll()){
                    if(childId == all.getId()){
                        childToAdd.add(childDao.getOne(childId));
                    }
                }
            }

            // ADDS EACH INDIVIDUAL Child TO PROFILE
            if (childToAdd != null){
                profile.setChildren(new ArrayList<>());
                for (Child child: childToAdd){
                    profile.getChildren().add(child);
                }
            }

            // Files handle

          profileDao.save(profile);
            uploadFileHandler(profile, m, uploadedFile);
            user.setProfile(profile);
            userDao.save(user);


        }

        return "redirect:/users/profile/" + user.getId();
    }
    private void uploadFileHandler(@Valid Profile profile, Model m, @RequestParam(name = "file") MultipartFile uploadedFile) {
        if(!uploadedFile.getOriginalFilename().isEmpty()){

            String filename = uploadedFile.getOriginalFilename().replace(" ", "_").toLowerCase();
            String filepath = Paths.get(uploadPath, filename).toString();
            File destinationFile = new File(filepath);

            // Try to save it in the server
            try {
                uploadedFile.transferTo(destinationFile);
                m.addAttribute("message", "File successfully uploaded!");
            } catch (IOException e) {
                e.printStackTrace();
                m.addAttribute("message", "Oops! Something went wrong! " + e);
            }

            Photo photo = new Photo(filename);
            photo.setProfile(profile);
            photoDao.save(photo);




        }
    }



    //    user details edit GET
    @GetMapping("/users/user-details-edit")
    public String showUserDetailsEdit(Model m){
        User user = usersService.loggedInUser();
         Profile profile = user.getProfile();
         m.addAttribute("profile", profile);
        m.addAttribute("hobbies", hobbyDao.findAll());
        m.addAttribute("traits", traitDao.findAll());
        m.addAttribute("branches", branchDao.findAll());
        m.addAttribute("ranks", rankDao.findAll());
        m.addAttribute("locations", locationDao.findAll());
        m.addAttribute("location", new Location());
        return "users/user-details-edit";
    }
//
//// user detail edit POST

    @PostMapping("/users/user-details-edit")
    public String submitUserDetailsEdit(
            @RequestParam(name="traits", required = false)ArrayList<Long> traitIds,
            @RequestParam(name="hobbies", required = false)ArrayList<Long> hobbyIds,
            @RequestParam(name="branch", required = false)Long branchId,
            @RequestParam(name="rank", required = false) Long rankId,
            @RequestParam(name="firstName", required = false) String firstName,
            @RequestParam(name="lastName", required = false) String lastName,
            @RequestParam(name="bio", required = false) String bio,
            @RequestParam(name="age", required = false) int age,
            @RequestParam(name="milSpouse", required = false) boolean millSpouse,
            @RequestParam(name="married", required = false) boolean married,
            @RequestParam(name="location", required = false) List<Location> locationId,
             @RequestParam(name="gender", required = false) boolean gender,
            @RequestParam(name = "form2", required = false) Location locationToBeCreated,
            @RequestParam(name="children", required = false)ArrayList<Long> childIds


    ){

        User user = usersService.loggedInUser();
        Profile profile = user.getProfile();

        profile.setBranch(branchDao.getOne(branchId));
        profile.setRank(rankDao.getOne(rankId));
//        locationDao.save(locationToBeCreated);


        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setBio(bio);
        profile.setage(age);
        profile.setMilSpouse(millSpouse);
       profile.setMarried(married);
       profile.setGender(gender);
       profile.setLocation(locationId);







        // FINDS THE Hobbies THAT WERE SELECTED BY USER
        List<Hobby> hobbiesToAdd = new ArrayList<>();
        for (long hobbyId : hobbyIds) {
            for (Hobby all: hobbyDao.findAll()){
                if(hobbyId == all.getId()){
                    hobbiesToAdd.add(hobbyDao.getOne(hobbyId));
                }
            }
        }

        // ADDS EACH INDIVIDUAL Hobby TO PROFILE
        if (hobbiesToAdd != null){
            profile.setHobbies(new ArrayList<>());
            for (Hobby hobby: hobbiesToAdd){
                profile.getHobbies().add(hobby);
            }
        }




        // FINDS THE TRAITS THAT WERE SELECTED BY USER
        List<Trait> traitsToAdd = new ArrayList<>();
        for (long traitId : traitIds) {
            for (Trait all: traitDao.findAll()){
                if(traitId == all.getId()){
                    traitsToAdd.add(traitDao.getOne(traitId));
                }
            }
        }

        // ADDS EACH INDIVIDUAL TRAIT TO PROFILE
        if (traitsToAdd != null){
            profile.setTraits(new ArrayList<>());
            for (Trait trait: traitsToAdd){
                profile.getTraits().add(trait);
            }
        }


        // FINDS THE Children THAT WERE SELECTED BY USER
        List<Child> childToAdd = new ArrayList<>();
        for (long childId : childIds) {
            for (Child all: childDao.findAll()){
                if(childId == all.getId()){
                    childToAdd.add(childDao.getOne(childId));
                }
            }
        }

        // ADDS EACH INDIVIDUAL Child TO PROFILE
        if (childToAdd != null){
            profile.setChildren(new ArrayList<>());
            for (Child child: childToAdd){
                profile.getChildren().add(child);
            }
        }


        profileDao.save(profile);

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

    @GetMapping("/users/{id}/decline-request")
    public String declineFriendRequest(@PathVariable long id ) {
        User userOne = usersService.loggedInUser();
        Relationship declineFriend = relationshipDao.getOne(id);
        FriendStatus status = FriendStatus.REJECTED;
        User requestedFriend = declineFriend.getUser();
        declineFriend.setStatus(status);


        Relationship relationshipOne = new Relationship(userOne, requestedFriend, status);
        relationshipDao.save(relationshipOne);
        relationshipDao.save(declineFriend);

        return "redirect:/users/profile/"+ userOne.getId();
    }

//location mapping

    @GetMapping("/users/location")

    public String showCreateForm(Model model) {
        model.addAttribute("location", new Location());

        return "users/location";
    }

    @PostMapping("/users/location")
    public String create(@ModelAttribute ("form2") Location locationToBeCreated){
//        locationToBeCreated.setProfiles(profileDao.getOne(1));

        locationDao.save(locationToBeCreated);
        return "redirect:/users/userdetails";
    }




    @GetMapping("/users/chat/{id}")
    public @ResponseBody List<User> getUser(Model model, @PathVariable long id) {


        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user2 = userDao.findById(id).orElse(null);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        return list;
    }



    @GetMapping("/users/chatbox/{id}")
    public String showchat(Model model, @PathVariable long id) {
        return "users/chatbox";
    }

    @GetMapping("/users/chat")

    public @ResponseBody User getUser(Model model) {

        return userDao.findById(1L).orElse(null);
    }


    @PostMapping("profile/comment/{id}")
    public String addMeetUpComment(@PathVariable long id, @RequestParam(name = "comment") String comment){
        User loggedInUser = usersService.loggedInUser();
        Profile profileBeingCommentedOn = profileDao.getOne(id);

        Comment newComment = new Comment(comment, profileBeingCommentedOn, loggedInUser);
        commentDao.save(newComment);

        System.out.println(newComment.getId());
        System.out.println(newComment.getComment());
        System.out.println(newComment);

        profileBeingCommentedOn.getComments().add(newComment);
        profileDao.save(profileBeingCommentedOn);

        loggedInUser.getComments().add(newComment);
        userDao.save(loggedInUser);


        return "redirect:/users/profile/" + profileBeingCommentedOn.getId();
    }

    @PostMapping("profile/comment/delete/")
    public String deleteProfileComment(@RequestParam(name = "deleteComment", required = false) Long commentID){
        System.out.println(commentID);
        Comment comment = commentDao.getOne(commentID);
        Profile profile = profileDao.getOne(comment.getProfile().getId());
        comment.setUser(new User("delete me"));
        System.out.println(comment);
        System.out.println(comment.getComment());
        System.out.println(comment.getId());

        commentDao.delete(comment);
        return "redirect:/users/profile/" + 2;

    }



}
