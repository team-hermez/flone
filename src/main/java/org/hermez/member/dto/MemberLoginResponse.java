package org.hermez.member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class MemberLoginResponse {
    private int memberId;
    private int roleId;
    private int memberStatus;
    private String email;
    private String socialLoginId;
    private String encodedPassword;
    private String name;
    private String phone;
}

