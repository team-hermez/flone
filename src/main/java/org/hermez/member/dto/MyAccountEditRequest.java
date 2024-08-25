package org.hermez.member.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyAccountEditRequest {
    private int memberId;
    private int roleId;
    private String email;
    private String name;
    private String password;
    private String passwordConfirm;
    private String phone;
    private String phoneConfirm;
}