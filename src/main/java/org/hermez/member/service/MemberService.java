package org.hermez.member.service;

import org.hermez.member.dto.MemberLoginRequest;
import org.hermez.member.dto.MemberRegisterRequest;
import org.hermez.member.dto.MyAccountEditRequest;
import org.hermez.member.dto.MyAccountResponse;
import org.hermez.member.model.Member;


public interface MemberService {
   void registerMember(MemberRegisterRequest memberRegisterRequest);
   void loginMember(MemberLoginRequest memberLoginRequest);
   void save(Member member);

   void insertMemberStatus(int memberId);

   MyAccountResponse getMyAccount(int memberId);

   void updateMyAccount(MyAccountEditRequest myAccountEditRequest);
}
