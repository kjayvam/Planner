package com.project.planner.dto;

import lombok.Data;

@Data
public class FriendDto {

    private String myId;    // 나의 id
    private String friendId;    // 친구의 id
    private FriendStatus status;

    public enum FriendStatus {
        REQUEST,   // 요청 중
        APPROVED    // 승인됨
    }

}
