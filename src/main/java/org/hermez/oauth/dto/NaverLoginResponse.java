package org.hermez.oauth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NaverLoginResponse {
    private int memberId;
    private int roleId;
    private String email;
    private String encodedPassword;
    private String name;
    private String phone;
}
