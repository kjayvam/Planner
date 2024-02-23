package com.project.planner.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // JPA
@Getter   // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class FriendEntity {

    @Id // JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA
    private Integer no;

    @ManyToOne
    @JoinColumn(name="user_no")
    private MemberEntity user_no;
    @ManyToOne
    @JoinColumn(name="friend_no")
    private MemberEntity friend_no;

}
