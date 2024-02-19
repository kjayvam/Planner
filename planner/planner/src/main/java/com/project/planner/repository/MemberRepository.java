package com.project.planner.repository;

import com.project.planner.dto.MemberDetailsDto;
import com.project.planner.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // SpringBoot
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {  // <entity, id 타입>

    @Query("select m.id from MemberEntity m where m.id = :id")    // JPA
    MemberEntity findById(@Param("id") String id);

    @Query("select m from MemberEntity m where m.id = :id")   // JPA
    MemberDetailsDto findAllBy(@Param("id") String id);

    @Query("select m.id from MemberEntity m where m.email = :email")
    List<MemberEntity> findByEmail(@Param("email") String email);

    boolean existsById(@Param("id") String id);

    MemberEntity findByIdAndEmail(String id, String email);

}