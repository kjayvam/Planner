package com.project.planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {

        // 현재 인증된 사용자의 아이디를 얻어오는 코드
//        String id = SecurityContextHolder.getContext().getAuthentication().getName();

//        return "members/login";
//        return "members/signup";
        return "index";
    }
}
