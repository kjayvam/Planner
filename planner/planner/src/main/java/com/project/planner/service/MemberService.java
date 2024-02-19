package com.project.planner.service;

import com.project.planner.dto.FindDto;
import com.project.planner.dto.LoginDto;
import com.project.planner.dto.MemberDetailsDto;
import com.project.planner.dto.SignUpDto;
import com.project.planner.entity.MemberEntity;
import com.project.planner.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service    // SpringBoot
public class MemberService {

    @Autowired  // SpringBoot
    private MemberRepository memberRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    public boolean idCheck(String id) {

        boolean isMember = memberRepository.existsById(id);
        if (isMember) {

            return true;
        }
        return false;
    }

    public boolean signUp(SignUpDto signUpDto) {

        MemberEntity member = new MemberEntity();
        modelMapper.map(signUpDto, member);

        if (signUpDto != null) {

            String encodedPw = passwordEncoder.encode(signUpDto.getPw());
            member.setPw(encodedPw);
            memberRepository.save(member);

            return true;
        }
        return false;
    }

    public MemberDetailsDto login(LoginDto loginDto) {

        String encodedPw = memberRepository.findById(loginDto.getId()).getPw();

        MemberDetailsDto member = memberRepository.findAllBy(loginDto.getId());
        if (passwordEncoder.matches(loginDto.getPw(), encodedPw)) {
            return member;
        }
        return null;
    }

    public List<FindDto> idFind(FindDto findDto) {

        List<MemberEntity> idEntityList = memberRepository.findByEmail(findDto.getEmail());

        List<FindDto> idDtoList = new ArrayList<>();

        for (MemberEntity entity : idEntityList) {

            FindDto dto = new FindDto();

            dto.setId(entity.getId());

            idDtoList.add(dto);
        }

        return idDtoList;
    }
}
