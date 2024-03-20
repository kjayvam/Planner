package com.project.planner.controller;

import com.project.planner.dto.*;
import com.project.planner.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    MemberService memberService;/*
    @Autowired
    private AuthenticationManager authenticationManager;
*/
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

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute SignUpDto signUpDto, Model model) {

        boolean result = memberService.signUp(signUpDto);

        if (result) {
            model.addAttribute("msg", "회원가입 성공");
            return "redirect:/";
        }
        model.addAttribute("msg", "회원가입 실패");
        return "./#signup";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, HttpSession session) {

        // 객체 생성 = 로그인 정보 받기
        MemberDetailsDto member = memberService.login(loginDto);

        // Security 인증 객체 생성 (id, pw)
        Authentication authentication = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
        // 홈페이지 어디서든 인증 정보 확인 가능
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // session에 회원 ID 저장
        session.setAttribute("ID", member.getMember().getId());
        // Security Context를 통해 사용자 인증을 관리 합니다. 세션에 사용자 정보를 저장하는 것은 필요한 경우에만 사용해야함
//        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
//        Authentication auth = authenticationManager.authenticate(authReq);
//
//        SecurityContextHolder.getContext().setAuthentication(auth);

//        return "forward:/";
//        return "redirect:/";
        return "redirect:/members/signup";
    }

    @PostMapping("/#logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }

    @PostMapping("/#accountFind")
    public String accountFind(@ModelAttribute FindDto findDto, Model model) {

        // ID 찾기
        if (findDto.getEmail() != null) {

            // email 넣으면 id 리스트가 나옵니다.
            List<FindDto> idList = memberService.idFind(findDto);

            model.addAttribute("idList", idList);

            return "./members/find";
        } else {
            // PW 찾기
            memberService.pwFind(findDto);
        }
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
/*

    @PostMapping("/#account")
    public String account(Authentication authentication) {
        MemberDetailsDto member = memberService.login();

        session.setAttribute("member", member);
        System.out.println(session.getId());
        System.out.println(session.get());

        return "./#account";
    }
*/

    @PutMapping("/#account/{id}")
    public String updateMember(@PathVariable String id, @ModelAttribute AccountDto accountDto) {

        memberService.updateMember(id, accountDto);

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
        return "redirect:/";
    }

}
