package org.hermez.member.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberLoginRequest {
    private String email;
    private String password;
    private int memberStatus;

    public MemberLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public MemberLoginRequest(String email) {
        this.email = email;
    }
}
