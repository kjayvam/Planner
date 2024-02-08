package com.project.planner.repository;

import com.project.planner.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // SpringBoot
public interface MemberRepository extends JpaRepository<Member, Integer> {  // <entity, id 타입>
    public Member findByMemberId(String id);

}
