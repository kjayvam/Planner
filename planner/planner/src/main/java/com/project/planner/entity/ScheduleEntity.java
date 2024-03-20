package com.project.planner.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
@Getter
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;
    private String color;           //  색상
    private String title;           //  제목
    private String content;         //  내용
    private String scheduleDate;    //  일정
    private String progress;        //  진행사황
    private String privacy;         //  공개여부

}
