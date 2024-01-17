package com.project.planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    @PostMapping("/calender/write")
    public String calenderForm() {

        System.out.print("작성 컨트롤러 : ");
        System.out.println("캘린더");

        return "redirect:/";
    }
}
