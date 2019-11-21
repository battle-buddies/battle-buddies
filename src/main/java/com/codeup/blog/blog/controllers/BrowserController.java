package com.codeup.blog.blog.controllers;


import com.codeup.blog.blog.repositories.HobbyRepository;
import com.codeup.blog.blog.repositories.ProfileRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrowserController {

    private HobbyRepository hobbyDao;
    private UserRepository userDao;
    private ProfileRepository profileDao;

    public BrowserController(HobbyRepository hobbyDao, UserRepository userDao, ProfileRepository profileDao){
        this.hobbyDao = hobbyDao;
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping("/browse/hobbies-index")
    public String showHobbiesIndex(Model vModel){
        vModel.addAttribute("hobbies", hobbyDao.findAll());
        return "browse/hobbies-index";
    }
}
