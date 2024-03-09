package com.project.planner.controller;

import com.project.planner.dto.FindDto;
import com.project.planner.dto.FriendDto;
import com.project.planner.service.Friendservice;
import com.project.planner.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    Friendservice friendService;
    @Autowired
    MemberService memberService;

    @PostMapping("/#friends")
    public String friendsList(@ModelAttribute FindDto findDto, Model model, String status) {

        List<FriendDto> list = friendService.friendsList(findDto, status);

        model.addAttribute("list", list);

        return "./#friends";
    }

    // 친구 요청하기
    @PostMapping("/#addFriend")
    public String requestFriend(HttpSession session, String friend_id) {

        if (memberService.idCheck(friend_id)) {
            friendService.requestFriend(session.getId(), friend_id);
        } else {
            System.out.println("에러");
        }

        return "./#friends";
    }

    // 친구 요청을 수락하기
    public String approved() {
        return "./#friends";
    }

    // 친구 요청을 거절하기
    public String refusal() {
        return "./#friends";
    }

    // 친구 삭제
    public String remove() {
        return "./#friends";
    }
/*
    친구 별명 설정
    친구 최근접속일 확인
    친구 공개글/사진첩 공유
    친구 생일 알림
    친구 위치 공유
*/
}
