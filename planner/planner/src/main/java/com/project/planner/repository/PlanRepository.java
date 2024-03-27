package com.project.planner.repository;

import com.project.planner.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<MemberEntity, Integer> {


}
