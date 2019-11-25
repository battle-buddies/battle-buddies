package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String landing() {
        return "home";
    }

    @GetMapping("/error")
    public String error(Exception exception, Model vModel){
        vModel.addAttribute("error", exception);
        return "error";

    }

}
