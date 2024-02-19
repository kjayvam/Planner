package com.project.planner.controller;

import com.project.planner.dto.FindDto;
import com.project.planner.dto.LoginDto;
import com.project.planner.dto.MemberDetailsDto;
import com.project.planner.dto.SignUpDto;
import com.project.planner.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller // SpringBoot
@RequestMapping("/member")  // SpringBoot
public class MemberController {

    @Autowired  // SpringBoot
    MemberService memberService;

    @ResponseBody   // SpringBoot
    @PostMapping("/#idCheck")  // SpringBoot
    public String idCheck(@RequestParam("id") String id) {  // SpringBoot

        String result = null;
        String start = "{\"result\":\"";
        String end = "\"}";

        if (memberService.idCheck(id)) {
            result = "pass";
        } else {
            result = "fail";
        }
        return start + result + end;    // {"result":"result"}
    }

    @ResponseBody
    @PostMapping("/#signUp")
    public String signUp(@ModelAttribute SignUpDto signUpDto, Model model) {

        boolean result = memberService.signUp(signUpDto);

        if (result) {
            model.addAttribute("msg", "회원가입 성공");
            return "redirect:/";
        }
        model.addAttribute("msg", "회원가입 실패");
        return "signup";
    }

    @PostMapping("/#login")
    public String login(LoginDto loginDto, HttpSession session) {

        MemberDetailsDto member = memberService.login(loginDto);

        session.setAttribute("member", member);

        return "redirect:/";
    }

    @PostMapping("/#logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }

    @GetMapping("/#idFind")
    public String idFind(@ModelAttribute FindDto findDto, Model model) {

        List<FindDto> idList = memberService.idFind(findDto);

        model.addAttribute("idList", idList);

        return "./#idSearchResult";
    }
}
