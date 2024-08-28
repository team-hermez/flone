package org.hermez.member.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 로그인 요청 DTO 입니다.
 *
 * 이 클래스는 회원 로그인 요청을 처리하기 위해 사용됩니다. 이메일, 비밀번호 및 회원 상태를 포함하고 있으며,
 * 다양한 생성자를 통해 유연한 객체 생성을 지원합니다.
 *
 * @author 김다은
 */
@Data
@NoArgsConstructor
public class MemberLoginRequest {

    /**
     * 회원의 이메일 주소.
     * 로그인 요청을 보낼 때 사용되는 이메일 주소입니다.
     */
    private String email;

    /**
     * 회원의 비밀번호.
     * 로그인 요청을 보낼 때 사용되는 비밀번호입니다.
     */
    private String password;

    /**
     * 회원 상태.
     * 로그인 과정에서 회원의 상태를 나타내는 필드로, 일반적으로 활성화 상태, 비활성화 상태 등을 의미할 수 있습니다.
     */
    private int memberStatus;

    /**
     * 이메일과 비밀번호를 사용하여 객체를 생성하는 생성자.
     *
     * @param email 회원의 이메일 주소
     * @param password 회원의 비밀번호
     */
    public MemberLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * 이메일만 사용하여 객체를 생성하는 생성자.
     *
     * @param email 회원의 이메일 주소
     */
    public MemberLoginRequest(String email) {
        this.email = email;
    }
}
