package org.hermez.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MemberRegisterResponse {
    private boolean success;
    private String message;
}
