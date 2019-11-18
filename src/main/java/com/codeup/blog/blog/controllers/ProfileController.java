package com.codeup.blog.blog.controllers;


import com.codeup.blog.blog.repositories.ProfileRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ProfileController {

    private ProfileRepository profileDao;
    private UserRepository userDao;

    public ProfileController(ProfileRepository profileDao, UserRepository userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping("/users/profile")
    public String showUserProfile(){
        return "users/profile";
    }
}
