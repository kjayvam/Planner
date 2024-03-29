package com.project.planner.repository;

import com.project.planner.dto.MemberDetailsDto;
import com.project.planner.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // SpringBoot
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {  // <entity, id 타입>

    // JPQL 쿼리문

    @Query("select m.id from MemberEntity m where m.id = :id")
    MemberEntity findById(@Param("id") String id);  // id로 id찾기

    @Query("select m from MemberEntity m where m.id = :id")
    MemberDetailsDto findAllBy(@Param("id") String id); // id로 맴버 찾기

    @Query("select m from MemberEntity m where m.id = :id")
    MemberEntity findAllById(@Param("id") String id);   // id로 맴버 찾기

    @Query("select m.id from MemberEntity m where m.email = :email")
    List<MemberEntity> findByEmail(@Param("email") String email);   // email로 id찾기

    boolean existsById(@Param("id") String id); // id 존재여부 확인(id체크)

    MemberEntity findByIdAndEmail(String id, String email); // id, email로 맴버 찾기(pw찾기)

    @Modifying
    @Query("update MemberEntity m set m.pw = :password where m.id = :id")
    void updatePassword(@Param("id") String id, @Param("password") String pw);  // 비밀번호 변경

    @Query("delete from MemberEntity m where m.id = :id")
    void deleteByMemberId(@Param("id") String id);  // 계정 삭제

}

