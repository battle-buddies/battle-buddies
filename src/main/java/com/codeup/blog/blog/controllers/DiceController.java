package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DiceController {

    @GetMapping("/rolldice")
    public String dice(){
        return "rolldice";
    }

    @GetMapping("/rolldice/{num}")
    public String rollDice(@PathVariable int num, Model vModel){
        int dice = (int)(Math.random() * 5) + 1;
        vModel.addAttribute("dice", dice);
        vModel.addAttribute("guess", num);


        if (dice == num){
            vModel.addAttribute("msg", "Good Guess!");
        }else {
            vModel.addAttribute("msg", "Bad guess. You should be ashamed honestly");
        }

        return "rolldice";
    }
}
