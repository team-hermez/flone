package org.hermez.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegisterRequest {

    @NotNull
    private String email;
    private int roleId;

    @NotEmpty(message = "{NotEmpty.member.password}")
    private String encodedPassword;

    @Pattern(regexp ="^[가-힣]*$", message = "")
    @NotEmpty(message = "{NotEmpty.member.name}")
    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "{Pattern.member.phone}")
    private String phone;

    private String socialLoginId;
    private LocalDateTime createdAt;
}
