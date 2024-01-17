package com.project.planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoveController {

    @GetMapping("/calender")
    public String calenderMove() {

        System.out.print("이동 컨트롤러 : ");
        System.out.println("캘린더");

        return "./calenders/calender";
    }

}
