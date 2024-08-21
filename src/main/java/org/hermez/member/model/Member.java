package org.hermez.member.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String email;
    private String password;
    private String password1;
    private String name;

    private String phone;

    private int memberId;
    private int role_Id;
    private LocalDateTime createAt;

    public Member(int role_Id, String email, String password, String name, String phone) {
        this.role_Id = role_Id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

