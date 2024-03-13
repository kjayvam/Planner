package com.project.planner.security;

import com.project.planner.dto.MemberDetailsDto;
import com.project.planner.entity.MemberEntity;
import com.project.planner.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsSecurity implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findById(id);

        if (member != null) {
            return new MemberDetailsDto(member);
        }
        return null;
    }
}
