package com.project.planner.repository;

import com.project.planner.dto.FriendDto;
import com.project.planner.dto.MemberDetailsDto;
import com.project.planner.entity.FriendEntity;
import com.project.planner.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // SpringBoot
public interface FriendRepository extends JpaRepository<FriendEntity, Integer> {  // <entity, id 타입>

    @Query("SELECT  m1.id, m2.id, f.status FROM FriendEntity f JOIN MemberEntity m1 ON f.user_no = m1.no JOIN MemberEntity m2 ON f.friend_no = m2.no WHERE m1.id = :id AND f.status = :status")
    List<FriendDto> findFriendsByMemberId(@Param("id") String id, @Param("status") String status);  // id로 친구리스트 뽑기

    @Modifying
    @Query(value = "UPDATE FriendEntity f SET f.status = 'APPROVED' WHERE f.status = 'REQUESTED' AND EXISTS(SELECT m1 FROM MemberEntity m1 WHERE f.user_no = m1.no AND m1.id = :myId) AND EXISTS(SELECT m2 FROM MemberEntity m2 WHERE f.friend_no = m2.no AND m2.id = :friendId)" )
    void updateFriendStatus(@Param("myId") String myId, @Param("friendId") String friendId);    // 친구 요청을 수락으로 변경

    @Modifying
    @Query("DELETE FROM FriendEntity f WHERE f.status = 'REQUESTED' AND EXISTS(SELECT m1 FROM MemberEntity m1 WHERE f.user_no = m1.no AND m1.id = :myId) AND EXISTS(SELECT m2 FROM MemberEntity m2 WHERE f.friend_no = m2.no AND m2.id = :myId)")
    void refusalFriend(@Param("myId") String myId, @Param("friendId") String friendId);  // 친구 요청 거절

    @Modifying
    @Query("DELETE FROM FriendEntity f WHERE EXISTS(SELECT m1 FROM MemberEntity m1 WHERE f.user_no = m1.no AND m1.id = :myId) AND EXISTS(SELECT m2 FROM MemberEntity m2 WHERE f.friend_no = m2.no AND m2.id = :myId)")
    void deleteFriend(@Param("myId") String myId, @Param("friendId") String friendId);  // 친구 요청 거절

}
