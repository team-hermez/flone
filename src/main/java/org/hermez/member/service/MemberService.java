package org.hermez.member.service;

import org.hermez.member.dto.*;
import org.hermez.member.model.Member;


public interface MemberService {
   void registerMember(MemberRegisterRequest memberRegisterRequest);
   void loginMember(MemberLoginRequest memberLoginRequest);
   void save(Member member);

   void insertMemberStatus(int memberId);

   MyAccountResponse getMyAccount(int memberId);

   void updateMyAccount(MyAccountEditRequest myAccountEditRequest);


}
