package com.project.planner.controller;

import com.project.planner.dto.*;
import com.project.planner.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
        return "./#signup";
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
        if (authentication != null) {
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

    @PostMapping("/#pwFind")
    public String pwFind(@ModelAttribute FindDto findDto) {

        memberService.pwFind(findDto);

        return "redirect:/";
    }

    @PutMapping("/#changePasswd")
    public String changePasswd(Authentication authentication, ChangePasswdDto changePasswd) {

        // 인증된 사용자 id 가져오기
        String id = authentication.getName();

        memberService.changePassword(id, changePasswd.getNewPassword());

        return "redirect:/";
    }

    @PostMapping("/#viewProfile")
    public String viewProfile(Authentication authentication, Model model) {

        model.addAttribute("account", authentication);

        return "./#account";
    }

    @PutMapping("/#account/{id}")
    public String updateMember(@PathVariable String id, @ModelAttribute SignUpDto signUpDto) {

        memberService.updateMember(id, signUpDto);

        return "redirect:/";
    }

    @DeleteMapping("/#member/{memberId}")
    public String deleteMember(@PathVariable String memberId, Authentication authentication) {

        // 인증된 사용자 가져오기(세션)
        User userDetail = (User) authentication.getPrincipal();

        // 인증된 사용자와 삭제 대상 사용자가 같은지 확인
        if (userDetail.getUsername().equals(memberId)) {

            memberService.deleteMember(memberId);
            return "redirect:/";
        }
        return "./#";
    }

    @PostMapping("/#friends")
    public String friendsList(@ModelAttribute FindDto findDto, Model model, String status) {


        List<FriendDto> list = memberService.friendsList(findDto, status);

        model.addAttribute("list", list);

        return "./#friends";
    }


    @PostMapping("/#addFriend")
    public String requestFriend(HttpSession session, String friend_id) {

        if (memberService.idCheck(friend_id)) {
            memberService.requestFriend(session.getId(), friend_id);
        } else {
            System.out.println("에러");
        }

        return "./#friends";
    }

}
