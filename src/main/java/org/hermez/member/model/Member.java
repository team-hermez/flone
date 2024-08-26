package org.hermez.member.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
public class Member {
    private int memberId;
    private int roleId;
    private String email;
    private String encodedPassword;
    private String name;
    private String phone;
    private String socialLoginId;
    private LocalDateTime createdAt;

    public Member(int roleId, String name, String email, String encodedPassword, String phone, String socialLoginId, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.socialLoginId = socialLoginId;
        this.phone = phone;
        this.createdAt = createdAt;
    }
    public Member(int memberId, int roleId, String email, String encodedPassword, String name, String phone, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.phone = phone;
        this.createdAt = createdAt;
    }

}

