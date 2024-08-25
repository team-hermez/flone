package org.hermez.member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class MemberLoginResponse {
    private int memberId;
    private int roleId;
    private String email;
    private String password;
    private String name;
    private String phone;
}

