package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.MeetUp;
import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.LocationRepository;
import com.codeup.blog.blog.repositories.MeetUpRepository;
import com.codeup.blog.blog.security.UserWithRoles;
import com.codeup.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MeetUpController {

    private MeetUpRepository meetUpDao;
    private LocationRepository locationDao;

    @Autowired
    private UserService usersService;

    public MeetUpController(MeetUpRepository meetUpDao, LocationRepository locationDao) {
        this.meetUpDao = meetUpDao;
        this.locationDao = locationDao;
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

        return "meetups/individual-meetup";
    }


    @GetMapping("meetups/create")
    public String showCreateMeetUp(MeetUp meetUp, Model vModel){
        vModel.addAttribute("meetup", meetUp);
        vModel.addAttribute("locations", locationDao.findAll());
        return "meetups/create-meetup";
    }

    @PostMapping("meetups/create")
    public String createMeetUp(@ModelAttribute ("meetup") MeetUp meetup, @RequestParam(name = "locationID", required = false) Long locationID){
        User loggedInUser = usersService.loggedInUser();
        meetup.setUser(loggedInUser);
        if (locationID != null){
            meetup.setLocation(locationDao.getOne(locationID));
        }

        meetUpDao.save(meetup);
        return "redirect:/meetups/";
    }
}
