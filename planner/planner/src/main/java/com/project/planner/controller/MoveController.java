package com.project.planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/move")
public class MoveController {
    @GetMapping("/signup")
    public String signup() {

        return "members/signup";
    }

    @GetMapping("/login")
    public String login() {

        return "members/login";
    }

    @GetMapping("/findAccount")
    public String findAccount() {

        return "members/find";
    }
}