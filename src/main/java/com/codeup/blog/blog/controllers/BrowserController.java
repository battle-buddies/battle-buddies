package com.codeup.blog.blog.controllers;


import com.codeup.blog.blog.models.Hobby;
import com.codeup.blog.blog.models.Profile;
import com.codeup.blog.blog.repositories.HobbyRepository;
import com.codeup.blog.blog.repositories.ProfileRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import com.sun.mail.auth.MD4;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/browse/hobbies/{id}")
    public String showIndividualHobby(@PathVariable long id, Model vModel){
        Hobby hobby = hobbyDao.getOne(id);
        List<Profile> profileList = new ArrayList<>();

        for (Profile profile : profileDao.findAll()) {
            for (Hobby userHobby: profile.getHobbies()){
                if (userHobby == hobby){
                    profileList.add(profile);
                }
            }
        }
        vModel.addAttribute("hobby", hobby);
        vModel.addAttribute("profiles", profileList);
        return "browse/hobby";
    }

}
