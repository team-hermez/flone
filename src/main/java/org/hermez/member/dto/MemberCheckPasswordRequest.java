package org.hermez.member.dto;

import lombok.Data;

/**
 * 데이터 전송 객체 (DTO) 클래스 입니다.
 *
 * @author 김다은
 */

@Data
public class MemberCheckPasswordRequest {
    private String passwordConfirm;
    private String memberId;
    private String email;
    private String phone;
}
