package com.project.planner.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // JPA
@Data   // Lombok
public class Board {

    @Id // JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA
    private Integer id;
    private String title;
    private String content;
    private String filename;
    private String filepath;

}
