package org.hermez.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberSocialLoginResponse {
    private int memberId;
    private int roleId;
    private int memberStatus;
    private String email;
    private String socialId;
    private String encodedPassword;
    private String name;
    private String phone;
    private LocalDateTime createdAt;

    public MemberSocialLoginResponse(int roleId, String name,
                                     String socialId, String email,
                                     String encodedPassword, String phone,
                                     int memberStatus, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.socialId = socialId;
        this.encodedPassword = encodedPassword;
        this.memberStatus = memberStatus;
        this.phone = phone;
        this.createdAt = createdAt;
    }
}
