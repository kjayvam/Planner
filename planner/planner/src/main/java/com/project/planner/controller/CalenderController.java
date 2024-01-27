package com.project.planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalenderController {
    @PostMapping("/calender/write")
    public String calenderForm() {

        System.out.print("작성 컨트롤러 : ");
        System.out.println("캘린더");

        return "redirect:/";
    }

    @GetMapping("/calender")
    public String calenderMove() {

        System.out.print("이동 컨트롤러 : ");
        System.out.println("캘린더");

        return "./calenders/calender";
    }
}
