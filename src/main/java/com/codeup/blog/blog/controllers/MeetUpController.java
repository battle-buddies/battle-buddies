package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.repositories.MeetUpRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeetUpController {

    private MeetUpRepository meetUpDao;

    public MeetUpController(MeetUpRepository meetUpDao) {
        this.meetUpDao = meetUpDao;
    }

    @GetMapping("/meetups/")
    public String showMeetUps(Model vModel){

        return "meetups/all-meetups";
    }
}
