package com.project.planner.service;

import com.project.planner.dto.FindDto;
import com.project.planner.dto.FriendDto;
import com.project.planner.entity.FriendEntity;
import com.project.planner.entity.MemberEntity;
import com.project.planner.repository.FriendRepository;
import com.project.planner.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private FriendRepository friendRepository;

    public List<FriendDto> friendsList(FindDto findDto, String status) {

        return friendRepository.findFriendsByMemberId(findDto.getId(), status);
    }

    public void requestFriend(String myId, String friendId) {


        MemberEntity myMember = memberRepository.findById(myId);
        MemberEntity friendMember = memberRepository.findById(friendId);

        FriendEntity friendRequest = new FriendEntity();

        friendRequest.setUser_no(myMember);
        friendRequest.setFriend_no(friendMember);
        friendRequest.setStatus("request");

        friendRepository.save(friendRequest);
    }

    public void approved(String myId, String friendId) {

        // 친구 요청 상태를 수락으로 변경하는 쿼리 실행
        friendRepository.updateFriendStatus(myId, friendId);

    }

    public void refusal(String myId, String friendId) {

        // 친구 요청 삭제
        friendRepository.refusalFriend(myId, friendId);
    }

    public void remove(String myId, String friendId) {

        // 친구를 삭제
        friendRepository.deleteFriend(myId, friendId);
    }
}
