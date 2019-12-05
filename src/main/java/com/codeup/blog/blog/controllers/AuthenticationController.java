package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AuthenticationController {
    private PasswordEncoder passwordEncoder;
    private UserRepository userDao;

    public AuthenticationController(PasswordEncoder passwordEncoder, UserRepository userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, @ModelAttribute String missing, @ModelAttribute String takenUsername, @ModelAttribute String takenEmail) {
        model.addAttribute("user", new User());

        if (missing != null){
            model.addAttribute("missing", missing);
        }

        if (takenEmail != null){
            model.addAttribute("takenEmail", takenEmail);
        }

        if (takenUsername != null){
            model.addAttribute("takenUsername", takenUsername);
        }
        return "users/login";
    }
}
