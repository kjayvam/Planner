package com.project.planner.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // JPA
@Data   // Lombok
public class Schedule {

    @Id // JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA
    private Integer no;
    private String color;           //  색상
    private String title;           //  제목
    private String content;         //  내용
    private String scheduleDate;    //  일정
    private String progress;        //  진행사황
    private String privacy;         //  공개여부

}
