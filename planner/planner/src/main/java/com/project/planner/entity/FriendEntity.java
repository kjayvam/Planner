package com.project.planner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "friends")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private MemberEntity user_no;
    @ManyToOne
    @JoinColumn(name = "friend_no")
    private MemberEntity friend_no;

    private String status;

}
