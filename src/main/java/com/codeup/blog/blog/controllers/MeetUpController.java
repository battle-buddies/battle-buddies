package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.Comment;
import com.codeup.blog.blog.models.MeetUp;
import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.CommentRepository;
import com.codeup.blog.blog.repositories.LocationRepository;
import com.codeup.blog.blog.repositories.MeetUpRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import com.codeup.blog.blog.security.UserWithRoles;
import com.codeup.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MeetUpController {

    private MeetUpRepository meetUpDao;
    private LocationRepository locationDao;
    private CommentRepository commentDao;
    private UserRepository userDao;

    @Autowired
    private UserService usersService;

    public MeetUpController(MeetUpRepository meetUpDao, LocationRepository locationDao, CommentRepository commentDao, UserRepository userDao) {
        this.meetUpDao = meetUpDao;
        this.locationDao = locationDao;
        this.commentDao = commentDao;
        this.userDao = userDao;
    }

    @GetMapping("/meetups/")
    public String showMeetUps(Model vModel){
        vModel.addAttribute("meetups", meetUpDao.findAll());
        return "meetups/all-meetups";
    }

    @GetMapping("meetups/{id}")
    public String showIndividualMeetUp(@PathVariable long id, Model vModel){
        MeetUp meetUp = meetUpDao.getOne(id);
        vModel.addAttribute("meetup", meetUp);
        vModel.addAttribute("signedInUser", usersService.loggedInUser());

        List<Comment> comments = new ArrayList<>();
        for (Comment comment: commentDao.findAll()) {
            if (comment.getMeetUp().getId() == meetUp.getId()){
                comments.add(comment);
            }
        }

        vModel.addAttribute("comments", comments);

        return "meetups/individual-meetup";
    }


    @GetMapping("meetups/create")
    public String showCreateMeetUp(MeetUp meetUp, Model vModel){
        vModel.addAttribute("meetup", meetUp);
        vModel.addAttribute("locations", locationDao.findAll());
        return "meetups/create-meetup";
    }

    @PostMapping("meetups/create")
    public String createMeetUp(@ModelAttribute ("meetup") MeetUp meetup, @RequestParam(name = "locationID", required = false) Long locationID, @RequestParam(name = "date")String date){
        User loggedInUser = usersService.loggedInUser();
        meetup.setUser(loggedInUser);
        if (locationID != null){
            meetup.setLocation(locationDao.getOne(locationID));
        }
        meetup.setDate(date);
        meetup.setCount(0);

        System.out.println(date);
        meetUpDao.save(meetup);
        return "redirect:/meetups/";
    }

    @PostMapping("meetups/interest/{id}")
    public String showInterestInMeetUp( @PathVariable long id){
        User user = usersService.loggedInUser();
        MeetUp meetUp = meetUpDao.getOne(id);


        ArrayList<User> usersToAdd = new ArrayList<>();

        usersToAdd.addAll(meetUp.getInterestedUsers());


        if(!usersToAdd.contains(user)){
            usersToAdd.add(user);
        }

        meetUp.setInterestedUsers(usersToAdd);

        int total = 0;
        for (User el: meetUp.getInterestedUsers()) {
            total += 1;

            System.out.println(el.getUsername());
        }

        meetUp.setCount(total);

        meetUpDao.save(meetUp);

        return "redirect:/meetups/" + meetUp.getId();
    }

    @PostMapping("meetups/delete/{id}")
    public String deleteMeetUp(@PathVariable long id){
        MeetUp meetUp = meetUpDao.getOne(id);

        meetUp.setUser(new User());

        meetUpDao.deleteById(id);

        return "redirect:/meetups/";
    }

    @PostMapping("meetups/comment/{id}")
    public String addMeetUpComment(@PathVariable long id, @RequestParam(name = "comment") String comment){
        User loggedInUser = usersService.loggedInUser();
        MeetUp meetUpBeingCommentedOn = meetUpDao.getOne(id);

        Comment newComment = new Comment(comment, meetUpBeingCommentedOn, loggedInUser);
        commentDao.save(newComment);

        meetUpBeingCommentedOn.getComments().add(newComment);
        meetUpDao.save(meetUpBeingCommentedOn);

        loggedInUser.getComments().add(newComment);
        userDao.save(loggedInUser);

        return "redirect:/meetups/" + meetUpBeingCommentedOn.getId();
    }

}


