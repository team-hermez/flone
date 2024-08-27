package org.hermez.member.dto;

import lombok.Data;

@Data
public class MemberCheckPasswordResponse {
    private String encodedPassword;

}
