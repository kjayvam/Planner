package com.project.planner.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "plan_item")
@Getter
public class PlansEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA
    private Integer no;
    private String color;           //  색상
}
