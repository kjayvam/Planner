package com.project.planner.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Security;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {

        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        return "index";
    }
}
