package org.hermez.member.service;

import org.hermez.member.dto.*;
import org.hermez.member.model.Member;

/**
 * 회원 관련 비즈니스 로직을 처리하는 서비스입니다.
 *
 * @author 김다은
 */
public interface MemberService {

   /**
    * 회원을 등록합니다.
    *
    * 이메일 및 전화번호 중복 검사를 수행한 후, 회원 정보를 저장합니다.
    * 비밀번호는 인코딩되어 저장됩니다.
    *
    * @param memberRegisterRequest 회원 등록 요청 데이터
    */
   void registerMember(MemberRegisterRequest memberRegisterRequest);

   /**
    * 회원 로그인 기능을 처리합니다.
    *
    * 이메일과 비밀번호를 확인하여 로그인을 처리하고, 세션을 설정합니다.
    *
    * @param memberLoginRequest 회원 로그인 요청 데이터
    */
   void loginMember(MemberLoginRequest memberLoginRequest);

   /**
    * 회원 정보를 저장합니다.
    *
    * @param member 저장할 회원 정보
    */
   void save(Member member);

   /**
    * 회원 상태를 업데이트합니다.
    *
    * 회원 ID를 기반으로 상태를 비활성화합니다.
    *
    * @param memberId 회원 ID
    */
   void insertMemberStatus(int memberId);

   /**
    * 회원의 정보를 조회합니다.
    *
    * @param memberId 회원 ID
    * @return 회원 정보
    */
   MyAccountResponse getMyAccount(int memberId);

   /**
    * 회원 정보를 수정합니다.
    *
    * 비밀번호는 인코딩되어 저장되며, 전화번호 중복 확인 후 업데이트됩니다.
    *
    * @param myAccountEditRequest 수정할 회원 정보
    */
   void updateMyAccount(MyAccountEditRequest myAccountEditRequest);

   /**
    * 비밀번호를 변경합니다.
    *
    * 이메일과 전화번호를 기반으로 비밀번호 변경 요청을 처리합니다.
    *
    * @param memberCheckPasswordRequest 비밀번호 변경 요청 데이터
    * @return 비밀번호 변경 성공 시 리다이렉션 URL
    */
   String changePassword(MemberCheckPasswordRequest memberCheckPasswordRequest);
}
