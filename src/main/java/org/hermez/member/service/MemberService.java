package org.hermez.member.service;

import org.hermez.member.dto.MemberLoginRequest;
import org.hermez.member.dto.MemberRegisterRequest;
import org.hermez.member.model.Member;

public interface MemberService {
   void registerMember(MemberRegisterRequest memberRegisterRequest);
   Member loginMember(MemberLoginRequest memberLoginRequest);
}
