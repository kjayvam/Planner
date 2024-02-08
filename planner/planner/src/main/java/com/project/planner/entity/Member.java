package com.project.planner.entity;

import lombok.Data;

import javax.persistence.*;

@Entity // JPA
@Data   // Lombok
public class Member {

    @Id // JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA
    private Integer no;
    @Column(unique=true)
    private String id;          //  아이디
    private String pw;          //  비밀번호
    private String profile;     //  프로필 사진
    private String name;        //  이름
    private String nickname;    //  닉네임
    private String email;       //  이메일

}
