package org.hermez.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Setter
@Getter
public class MyAccountEditRequest {
    private int memberId;
    private int roleId;
    private String email;
    private String name;
    private String passwordConfirm;

    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "{Pattern.member.phone}")
    private String phone;
    private String phoneConfirm;
}