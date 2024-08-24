package org.hermez.member.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Member {
    private int memberId;
    private int roleId;
    private String email;
    private String password;
    private String passwordNot;
    private String name;
    private String phone;
    private LocalDateTime createdAt;

    public Member(int roleId, String email, String password, String name, String phone, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.createdAt = createdAt;
    }
    public Member(int memberId, int roleId, String email, String password, String name, String phone, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

