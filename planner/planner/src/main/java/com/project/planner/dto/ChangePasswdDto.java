package com.project.planner.dto;

import lombok.Data;

@Data   // Lombok
public class ChangePasswdDto {

    private String oldPassword; // 예전 비밀번호
    private String newPassword; // 새로운 비밀번호
}
