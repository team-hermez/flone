package org.hermez.member.dto;

import lombok.Getter;

/**
 * 계정 정보 응답 DTO 입니다.
 *
 * 이 클래스는 사용자 계정 정보를 조회한 결과를 담기 위해 사용됩니다.
 * 회원의 ID, 역할 ID, 이메일, 이름, 비밀번호 및 전화번호를 포함합니다.
 *
 * @author 김다은
 */
@Getter
public class MyAccountResponse {

    /**
     * 회원의 고유 ID.
     * 계정 정보 조회 응답에서 회원을 식별하는 데 사용됩니다.
     */
    private int memberId;

    /**
     * 회원의 역할 ID.
     * 계정 정보 조회 응답에서 회원의 역할을 나타내며, 권한 관리에 사용될 수 있습니다.
     */
    private int roleId;

    /**
     * 회원의 이메일 주소.
     * 계정 정보 조회 응답에서 회원의 이메일 주소를 제공합니다.
     */
    private String email;

    /**
     * 회원의 이름.
     * 계정 정보 조회 응답에서 회원의 이름을 제공합니다.
     */
    private String name;

    /**
     * 회원의 비밀번호.
     * 계정 정보 조회 응답에서 회원의 비밀번호를 제공합니다. 보안상의 이유로 비밀번호는 암호화되어 저장되어야 합니다.
     */
    private String password;

    /**
     * 회원의 전화번호.
     * 계정 정보 조회 응답에서 회원의 전화번호를 제공합니다.
     */
    private String phone;
}
