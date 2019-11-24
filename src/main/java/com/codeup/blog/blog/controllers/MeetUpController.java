package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.MeetUp;
import com.codeup.blog.blog.repositories.MeetUpRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MeetUpController {

    private MeetUpRepository meetUpDao;

    public MeetUpController(MeetUpRepository meetUpDao) {
        this.meetUpDao = meetUpDao;
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
        vModel.addAttribute("meetup", meetUp)

        return "meetups/create-meetup";
    }

    @PostMapping("meetups/create")
    public String createMeetUp(){

        return "redirect:/meetups/all-meetups";
    }
}
