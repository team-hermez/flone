package org.hermez.member.dto;

import lombok.Data;

/***
 * 데이터 전송 객체 (DTO) 클래스
 *
 * @author 김다은
 */
@Data
public class MemberCheckPasswordResponse {
    private String encodedPassword;

}
