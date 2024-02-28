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

    @Query("SELECT  m.id, m2.id, f.status FROM FriendEntity f JOIN MemberEntity m ON f.user_no = m.no JOIN MemberEntity m2 ON f.friend_no = m2.no WHERE m.id = :id AND f.status = :status")
    List<FriendDto> findFriendsByMemberId(@Param("id") String id, @Param("status") String status);  // id로 친구리스트 뽑기
}

