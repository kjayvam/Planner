package com.project.planner.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "alarm")
@Getter
public class AlarmEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;
    private String time_alarm;      //  정시 알람 (ex. 오전 9시 알람)
    private String advance_alarm;   //  사전 알람 (ex. 5분전 알람)
    private String recurring_alarm; //  반복 알람 (ex. 매주 알람)
    private String user_no;
}
