package com.project.planner.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // JPA
@Data   // Lombok
public class Alarm {


    @Id // JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA
    private Integer no;
    private String time_alarm;      //  정시 알람 (ex. 오전 9시 알람)
    private String advance_alarm;   //  사전 알람 (ex. 5분전 알람)
    private String recurring_alarm; //  반복 알람 (ex. 매주 알람)
    private String user_no;
}
