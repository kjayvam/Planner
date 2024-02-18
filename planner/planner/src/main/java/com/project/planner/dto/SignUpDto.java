package com.project.planner.dto;

import lombok.Data;

@Data   // Lombok
public class SignUpDto {

    private String id;          //  아이디
    private String pw;          //  비밀번호
    private String profile;     //  프로필 사진
    private String name;        //  이름
    private String nickname;    //  닉네임
    private String email;       //  이메일

}
