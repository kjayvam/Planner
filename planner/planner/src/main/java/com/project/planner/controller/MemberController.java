package com.project.planner.controller;

import com.project.planner.entity.Member;
import com.project.planner.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // SpringBoot
@RequestMapping("/member")  // SpringBoot
public class MemberController {

    @Autowired  // SpringBoot
    MemberService memberService;

    @ResponseBody   // SpringBoot
    @GetMapping("/#")  // SpringBoot
    public String idCheck(@RequestParam("id") String id) {  // SpringBoot

        String result = null;
        String start = "{\"result\":\"";
        String end = "\"}";

        if (memberService.idCheck(id)) {
            result = "pass";
        } else {
            result = "fail";
        }
        return start + result + end;
    }

    @ResponseBody
    @PostMapping("/#")
    public String signUp(@ModelAttribute Member member, Model model) {

        boolean result = memberService.signUp(member);

        if (result) {
            model.addAttribute("msg", "회원가입 성공");
            return "redirect:/";
        }
        model.addAttribute("msg", "회원가입 실패");
        return "signup";
    }

    @PostMapping("#")
    public String login(Member member) {

        // 미완성

        return "redirect:/";
    }

}
