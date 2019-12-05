package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping("/sign-up")
//    public String showSignupForm(Model model){
//        model.addAttribute("user", new User());
//        return "users/sign-up";
//    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, Model vModel){
        if (userDao.findByUsername(user.getUsername()) != null){

            vModel.addAttribute("takenUsername", "That Username is already taken. ");
            return "/users/login";

        }else if(userDao.findByEmail(user.getEmail()) != null){

            vModel.addAttribute("takenEmail", "That Email is already in use. ");
            return "/users/login";

        }else if (user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getUsername().isEmpty()){

            vModel.addAttribute("missing", "Fill out all the forms" );
            return "/users/login";

        }else {

            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            userDao.save(user);
            return "redirect:/login";

        }
    }

}


