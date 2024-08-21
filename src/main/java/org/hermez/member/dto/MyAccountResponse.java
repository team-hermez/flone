package org.hermez.member.dto;

import lombok.Getter;

@Getter
public class MyAccountResponse {
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    private String birthDate;

    public MyAccountResponse(String email, String name, String password, String phoneNumber, String birthDate) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }
}