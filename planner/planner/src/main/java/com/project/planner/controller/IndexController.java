package com.project.planner.controller;

import com.project.planner.dto.PlanDto;
import com.project.planner.dto.SignUpDto;
import com.project.planner.service.MemberService;
import com.project.planner.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private PlanService planService;
    @GetMapping("/")
    public String index() {

        // 현재 인증된 사용자의 아이디를 얻어오는 코드
//        String id = SecurityContextHolder.getContext().getAuthentication().getName();

//        return "members/login";
//        return "members/signup";
        return "index";
    }

    @PostMapping("/insertPlan")
    public String insertPlan(@ModelAttribute PlanDto planDto, Model model){
        planService.insert(planDto);

        return "";
    }

}
