package org.hermez.member.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponse {
    private int memberId;
    private int roleId;
    private String email;
    private String name;
    private String password;
    private String phone;
    private LocalDateTime createdAt;
}