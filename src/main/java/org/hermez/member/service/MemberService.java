package org.hermez.member.service;

import org.hermez.member.dto.MemberLoginRequest;
import org.hermez.member.dto.MemberRegisterRequest;
import org.hermez.member.model.Member;
import org.springframework.stereotype.Service;



public interface MemberService {
   void registerMember(MemberRegisterRequest memberRegisterRequest);
   void loginMember(MemberLoginRequest memberLoginRequest);
   void save(Member member);
}
