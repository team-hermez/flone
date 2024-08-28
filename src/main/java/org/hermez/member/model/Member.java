package org.hermez.member.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 회원 정보를 나타내는 데이터 모델 클래스입니다.
 *
 * 이 클래스는 회원의 기본 정보를 담고 있으며, 회원 등록 및 조회 시 사용됩니다.
 * 회원의 ID, 역할, 이메일, 비밀번호, 이름, 전화번호, 상태, 소셜 로그인 ID, 생성 일시 등의 정보를 포함합니다.
 * Lombok의 @Getter, @Setter, @NoArgsConstructor 어노테이션을 사용하여 getter, setter 및 기본 생성자를 자동으로 생성합니다.
 *
 * @author 김다은
 */
@Getter
@Setter
@NoArgsConstructor
public class Member {

    /**
     * 회원 ID
     */
    private int memberId;

    /**
     * 역할 ID
     */
    private int roleId;

    /**
     * 이메일 주소
     */
    private String email;

    /**
     * 인코딩된 비밀번호
     */
    private String encodedPassword;

    /**
     * 회원 이름
     */
    private String name;

    /**
     * 전화번호
     */
    private String phone;

    /**
     * 회원 상태 (활성/비활성 등)
     */
    private int memberStatus;

    /**
     * 소셜 로그인 ID (소셜 로그인 회원의 경우)
     */
    private String socialLoginId;

    /**
     * 회원 가입 일시
     */
    private LocalDateTime createdAt;

    /**
     * 모든 필드를 포함한 생성자.
     *
     * @param roleId 역할 ID
     * @param name 이름
     * @param email 이메일 주소
     * @param encodedPassword 인코딩된 비밀번호
     * @param phone 전화번호
     * @param memberStatus 회원 상태
     * @param createdAt 회원 가입 일시
     */
    public Member(int roleId, String name, String email, String encodedPassword, String phone, int memberStatus, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.memberStatus = memberStatus;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    /**
     * 소셜 로그인 ID를 포함한 생성자.
     *
     * @param roleId 역할 ID
     * @param name 이름
     * @param socialLoginId 소셜 로그인 ID
     * @param email 이메일 주소
     * @param encodedPassword 인코딩된 비밀번호
     * @param phone 전화번호
     * @param memberStatus 회원 상태
     * @param createdAt 회원 가입 일시
     */
    public Member(int roleId, String name, String socialLoginId, String email, String encodedPassword, String phone, int memberStatus, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.socialLoginId = socialLoginId;
        this.memberStatus = memberStatus;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    /**
     * 회원 ID를 포함한 생성자.
     *
     * @param memberId 회원 ID
     * @param roleId 역할 ID
     * @param email 이메일 주소
     * @param encodedPassword 인코딩된 비밀번호
     * @param name 이름
     * @param phone 전화번호
     * @param memberStatus 회원 상태
     * @param createdAt 회원 가입 일시
     */
    public Member(int memberId, int roleId, String email, String encodedPassword, String name, String phone, int memberStatus, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.memberStatus = memberStatus;
        this.encodedPassword = encodedPassword;
        this.phone = phone;
        this.createdAt = createdAt;
    }
}
