package org.hermez.member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  회원 로그인 응답 DTO 입니다.
 *
 * 이 클래스는 로그인 요청에 대한 응답으로, 회원의 다양한 정보를 담기 위해 사용됩니다.
 * 회원의 ID, 역할, 상태, 이메일, 소셜 로그인 ID, 암호화된 비밀번호, 이름, 전화번호를 포함하고 있습니다.
 *
 * @author 김다은
 */
@Data
@Getter
@Setter
@NoArgsConstructor
public class MemberLoginResponse {

    /**
     * 회원의 고유 ID.
     * 로그인 응답에서 회원을 식별하는 데 사용됩니다.
     */
    private int memberId;

    /**
     * 회원의 역할 ID.
     * 로그인 응답에서 회원의 역할을 나타내며, 권한 관리를 위해 사용될 수 있습니다.
     */
    private int roleId;

    /**
     * 회원 상태.
     * 로그인 응답에서 회원의 현재 상태를 나타냅니다. 예를 들어, 활성화, 비활성화 상태 등을 의미할 수 있습니다.
     */
    private int memberStatus;

    /**
     * 회원의 이메일 주소.
     * 로그인 응답에서 회원의 이메일 주소를 제공합니다.
     */
    private String email;

    /**
     * 소셜 로그인 ID.
     * 회원이 소셜 로그인을 사용하는 경우 제공되는 소셜 로그인 ID입니다.
     */
    private String socialLoginId;

    /**
     * 암호화된 비밀번호.
     * 로그인 응답에서 제공되는 회원의 암호화된 비밀번호입니다.
     */
    private String encodedPassword;

    /**
     * 회원의 이름.
     * 로그인 응답에서 회원의 이름을 제공합니다.
     */
    private String name;

    /**
     * 회원의 전화번호.
     * 로그인 응답에서 회원의 전화번호를 제공합니다.
     */
    private String phone;
}
