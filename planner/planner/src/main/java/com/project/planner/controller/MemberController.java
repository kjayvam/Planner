package com.project.planner.controller;

import com.project.planner.dto.*;
import com.project.planner.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private MemberService memberService;
    @Autowired
    private AuthenticationManager authenticationManager;

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
    public String login(@ModelAttribute LoginDto loginDto) {

        // 객체 생성 = 로그인 정보 받기
        MemberDetailsDto member = memberService.login(loginDto);
        if (member == null) {
            return "redirect:/members/login?error=true";
        }

        try {
            // 인증 토큰 생성
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.getId(), loginDto.getPw());
            // 정상인지 로그인 시도 해봄. authenticationManager로 로그인 시도를 하면
            // PrincipalDetailsService가 호출 loadUserByUsername() 함수가 실행된 후 정상이면 authentication이 리턴됨.
            // authentication이 정상 리턴된다는 것은 -> DB에 있는 username과 password가 일치한다는 것.
            Authentication authentication = authenticationManager.authenticate(authToken);
            // 홈페이지 어디서든 인증 정보 확인 가능
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "members/signup";    // 인증 성공 후 리디렉트할 경로
        } catch (Exception e) {
//        return "forward:/";
//        return "redirect:/";
            e.printStackTrace();
            return "redirect:/members/login";   // 인증 실패 시 리디렉트할 경로
        }
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

    @PostMapping("/#account")
    public String account(Authentication authentication, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Authentication 객체가 null이 아니고, 사용자가 인증된 상태인지 확인합니다.
        if (authentication != null && authentication.isAuthenticated()) {
            String id = null;
            // 주요 사용자 정보(Principal)를 가져옵니다.
            Object principal = authentication.getPrincipal();

            // 상속, 클래스 비교
            if (principal instanceof UserDetails) {
                // id 가져오기
                id = ((UserDetails) principal).getUsername();
            } else {
                id = principal.toString();
            }

            System.out.println("Current user: " + id);
            // 사용자 정보 view에 보내기
            model.addAttribute("Details", principal);
        }

        return "./#account";
    }

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
