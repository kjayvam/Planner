package com.project.planner.repository;

import com.project.planner.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // SpringBoot
public interface BoardRepository extends JpaRepository<Board, Integer> {    // <entity, id 타입>

    // 키워드 검색
    Page<Board> findByTitleContaining(String serchKeyword, Pageable pageable);
}
