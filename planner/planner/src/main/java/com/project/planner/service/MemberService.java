package com.project.planner.service;

import com.project.planner.dto.*;
import com.project.planner.entity.FriendEntity;
import com.project.planner.entity.MemberEntity;
import com.project.planner.repository.FriendRepository;
import com.project.planner.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service    // SpringBoot
public class MemberService {

    @Autowired  // SpringBoot
    private MemberRepository memberRepository;
    @Autowired  // SpringBoot
    private FriendRepository friendRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JavaMailSender mailSender;

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

    public void pwFind(FindDto findDto) {

        MemberEntity memberEntity = memberRepository.findByIdAndEmail(findDto.getId(), findDto.getEmail());

        // 임시 비밀번호 생성
        String tempPassword = UUID.randomUUID().toString().split("-")[0];

        // 임시 비밀번호 이메일 발송
        sendEmail(findDto.getEmail(), tempPassword);

        // 임시 비밀번호로 pw 변경
        memberEntity.setPw(tempPassword);
        memberRepository.save(memberEntity);
    }

    // (스프링 비밀번호찾기 - 단순 이메일 전송)[https://velog.io/@jinvicky/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B9%84%EB%B2%88%EC%B0%BE%EA%B8%B0-%EB%8B%A8%EC%88%9C-%EC%9D%B4%EB%A9%94%EC%9D%BC-%EC%A0%84%EC%86%A1]
    public void sendEmail(String email, String tempPassword) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        String msg = "<p>임시 비밀번호</p> ";
        mailMessage.setText(msg + tempPassword);

        mailSender.send(mailMessage);
    }

    public List<FriendDto> friendsList(FindDto findDto, String status) {

        return friendRepository.findFriendsByMemberId(findDto.getId(), status);
    }

    public void requestFriend(String myId, String friendId) {

        MemberEntity myMember = memberRepository.findById(myId);
        MemberEntity friendMember = memberRepository.findById(friendId);

        FriendEntity friendRequest = new FriendEntity();

        friendRequest.setUser_no(myMember);
        friendRequest.setFriend_no(friendMember);
        friendRequest.setStatus("requested");

        friendRepository.save(friendRequest);
    }

}
