package com.project.planner.service;

import com.project.planner.entity.Member;
import com.project.planner.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    // SpringBoot
public class MemberService {

    @Autowired  // SpringBoot
    private MemberRepository memberRepository;

    public boolean idCheck(String id) {

        Member member = memberRepository.findByMemberId(id);

        return member != null;
    }


    public boolean signUp(Member member) {

        memberRepository.save(member);

        return true;
    }
}
