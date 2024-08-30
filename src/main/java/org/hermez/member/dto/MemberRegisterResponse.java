package org.hermez.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원 등록 응답 DTO 입니다.
 *
 * 이 클래스는 회원 등록 요청에 대한 응답을 처리하기 위해 사용됩니다.
 * 등록 성공 여부와 메시지를 포함하고 있습니다.
 *
 * @author 김다은
 */
@NoArgsConstructor
@Setter
@Getter
public class MemberRegisterResponse {

    /**
     * 등록 성공 여부.
     * 회원 등록 요청이 성공했는지 여부를 나타냅니다.
     */
    private boolean success;

    /**
     * 응답 메시지.
     * 회원 등록 요청에 대한 결과를 설명하는 메시지입니다. 성공 또는 실패의 이유를 포함할 수 있습니다.
     */
    private String message;
}