package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {


    @Autowired
    private UserService usersService;
    @Autowired
    UserService usersSvc;

    @GetMapping("/")
    public String landing(Model Model) {
        User loggedInUser = usersService.loggedInUser();
        Model.addAttribute( "user",loggedInUser);

        return "home";
    }

    @GetMapping("/error")
    public String error(Exception exception, Model vModel){
        vModel.addAttribute("error", exception);
        return "error";

    }

}
