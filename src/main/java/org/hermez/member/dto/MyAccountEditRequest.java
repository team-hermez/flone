package org.hermez.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 * 계정 수정 요청 DTO 입니다.
 *
 * 이 클래스는 사용자 계정 정보를 수정하기 위한 요청을 처리하기 위해 사용됩니다.
 * 회원 ID, 역할 ID, 이메일, 이름, 비밀번호 확인, 전화번호 및 전화번호 확인을 포함합니다.
 *
 * @author 김다은
 */
@Setter
@Getter
public class MyAccountEditRequest {

    /**
     * 회원의 고유 ID.
     * 계정 수정 요청에서 회원을 식별하는 데 사용됩니다.
     */
    private int memberId;

    /**
     * 회원의 역할 ID.
     * 계정 수정 요청에서 회원의 역할을 나타내며, 권한 관리에 사용될 수 있습니다.
     */
    private int roleId;

    /**
     * 회원의 이메일 주소.
     * 계정 수정 요청에서 사용자의 이메일 주소를 수정합니다.
     */
    private String email;

    /**
     * 회원의 이름.
     * 계정 수정 요청에서 사용자의 이름을 수정합니다.
     */
    private String name;

    /**
     * 비밀번호 확인.
     * 계정 수정 요청에서 비밀번호를 확인하기 위해 사용됩니다.
     */
    private String passwordConfirm;

    /**
     * 회원의 전화번호.
     * 계정 수정 요청에서 사용자의 전화번호를 수정합니다. 형식은 "010-1234-5678"입니다.
     */
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "{Pattern.member.phone}")
    private String phone;

    /**
     * 전화번호 확인.
     * 계정 수정 요청에서 전화번호를 확인하기 위해 사용됩니다.
     */
    private String phoneConfirm;
}
