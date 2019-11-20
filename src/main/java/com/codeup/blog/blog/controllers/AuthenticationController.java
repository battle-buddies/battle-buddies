package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    private PasswordEncoder passwordEncoder;
    private UserRepository userDao;

    public AuthenticationController(PasswordEncoder passwordEncoder, UserRepository userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }
}
