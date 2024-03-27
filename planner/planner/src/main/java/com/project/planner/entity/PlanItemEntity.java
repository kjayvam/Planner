package com.project.planner.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "plan_item")
@Getter
public class PlanItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;
    private String title;       //  제목
    private String content;     //  내용
    private String startDate;   //  시작 일정
    private String endDate;     //  마감 일정
    private String progress;    //  진행사황
    private String privacy;     //  공개여부
    private String location;    // 장소
}
