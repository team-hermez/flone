package org.hermez.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * 회원 등록 요청 DTO 입니다.
 *
 * 이 클래스는 회원 가입 요청을 처리하기 위해 사용됩니다. 이메일, 역할 ID, 회원 상태, 비밀번호, 이름, 전화번호,
 * 소셜 로그인 ID 및 생성 시간을 포함하고 있습니다.
 *
 * @author 김다은
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegisterRequest {

    /**
     * 회원의 이메일 주소.
     * 회원 가입 시 필수로 입력해야 하며, null 값을 허용하지 않습니다.
     */
    @NotNull
    private String email;

    /**
     * 회원의 역할 ID.
     * 회원의 역할을 나타내며, 시스템에서 정의된 역할을 기준으로 설정됩니다.
     */
    private int roleId;

    /**
     * 회원 상태.
     * 회원의 상태를 나타내며, 예를 들어 활성화, 비활성화 등을 의미할 수 있습니다.
     */
    private int memberStatus;

    /**
     * 암호화된 비밀번호.
     * 회원 가입 시 필수로 입력해야 하며, 비밀번호가 비어 있으면 안 됩니다.
     */
    @NotEmpty(message = "{NotEmpty.member.password}")
    private String encodedPassword;

    /**
     * 회원의 이름.
     * 한글로만 입력해야 하며, 비어 있으면 안 됩니다.
     */
    @Pattern(regexp = "^[가-힣]*$", message = "{Pattern.member.name}")
    @NotEmpty(message = "{NotEmpty.member.name}")
    private String name;

    /**
     * 회원의 전화번호.
     * 특정 형식의 전화번호만 허용되며, 예: 010-1234-5678.
     */
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "{Pattern.member.phone}")
    private String phone;

    /**
     * 소셜 로그인 ID.
     * 소셜 로그인 시 사용되는 ID로, 선택 사항입니다.
     */
    private String socialLoginId;

    /**
     * 회원 가입 시 생성 시간.
     * 회원이 등록된 시간을 기록하며, 기본값은 시스템에 의해 자동 설정됩니다.
     */
    private LocalDateTime createdAt;
}