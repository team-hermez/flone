package org.hermez.member.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private int memberId;
    private int roleId;
    private String email;
    private String encodedPassword;
    private String name;
    private String phone;
    private int memberStatus;
    private String socialLoginId;
    private LocalDateTime createdAt;

    public Member(int roleId, String name, String socialLoginId,String email, String encodedPassword, String phone, int memberStatus, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.socialLoginId = socialLoginId;
        this.memberStatus = memberStatus;
        this.phone = phone;
        this.createdAt = createdAt;
    }
    public Member(int memberId, int roleId, String email, String encodedPassword, String name, String phone, int memberStatus, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.memberStatus = memberStatus;
        this.encodedPassword = encodedPassword;
        this.phone = phone;
        this.createdAt = createdAt;
    }

}

