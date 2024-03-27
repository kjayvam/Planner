package com.project.planner.dto;

import lombok.Data;

@Data   // Lombok
public class PlanDto {
    private String title;          //  플랜제목
    private String startDate;          //  시작날짜
    private String endDate;          //  종료날짜
    private String type;        //  타입
    private String location;    //  장소
    private String advance_alarm;       //  사전알람
    private String content;       //  텍스트
}
