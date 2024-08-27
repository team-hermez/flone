package org.hermez.member.dto;

import lombok.Data;

@Data
public class MemberCheckPasswordRequest {
    private String passwordConfirm;
    private String memberId;
    private String email;
    private String phone;
}
