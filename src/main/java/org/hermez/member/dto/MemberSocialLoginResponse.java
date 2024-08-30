package org.hermez.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 소셜 로그인 응답 DTO 입니다.
 *
 * 이 클래스는 소셜 로그인 요청에 대한 응답을 처리하기 위해 사용됩니다.
 * 회원의 ID, 역할, 상태, 이메일, 소셜 로그인 ID, 암호화된 비밀번호, 이름, 전화번호 및 생성 시간을 포함하고 있습니다.
 *
 * @author 김다은
 */
@Getter
@Setter
public class MemberSocialLoginResponse {

    /**
     * 회원의 고유 ID.
     * 소셜 로그인 응답에서 회원을 식별하는 데 사용됩니다.
     */
    private int memberId;

    /**
     * 회원의 역할 ID.
     * 소셜 로그인 응답에서 회원의 역할을 나타내며, 권한 관리에 사용될 수 있습니다.
     */
    private int roleId;

    /**
     * 회원 상태.
     * 소셜 로그인 응답에서 회원의 현재 상태를 나타냅니다. 예를 들어 활성화, 비활성화 등을 의미할 수 있습니다.
     */
    private int memberStatus;

    /**
     * 회원의 이메일 주소.
     * 소셜 로그인 응답에서 회원의 이메일 주소를 제공합니다.
     */
    private String email;

    /**
     * 소셜 로그인 ID.
     * 소셜 로그인 시 제공되는 ID로, NAVER 계정의 고유 ID입니다.
     */
    private String socialId;

    /**
     * 암호화된 비밀번호.
     * 소셜 로그인 응답에서 제공되는 암호화된 비밀번호입니다.
     */
    private String encodedPassword;

    /**
     * 회원의 이름.
     * 소셜 로그인 응답에서 회원의 이름을 제공합니다.
     */
    private String name;

    /**
     * 회원의 전화번호.
     * 소셜 로그인 응답에서 회원의 전화번호를 제공합니다.
     */
    private String phone;

    /**
     * 회원 가입 시 생성 시간.
     * 회원이 등록된 시간을 기록하며, 시스템에 의해 자동 설정됩니다.
     */
    private LocalDateTime createdAt;

    /**
     * 소셜 로그인 응답을 생성하는 생성자.
     *
     * @param roleId 회원의 역할 ID
     * @param name 회원의 이름
     * @param socialId 소셜 로그인 ID
     * @param email 회원의 이메일 주소
     * @param encodedPassword 암호화된 비밀번호
     * @param phone 회원의 전화번호
     * @param memberStatus 회원의 상태
     * @param createdAt 회원 가입 시 생성 시간
     */
    public MemberSocialLoginResponse(int roleId, String name,
                                     String socialId, String email,
                                     String encodedPassword, String phone,
                                     int memberStatus, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.socialId = socialId;
        this.encodedPassword = encodedPassword;
        this.memberStatus = memberStatus;
        this.phone = phone;
        this.createdAt = createdAt;
    }
}
