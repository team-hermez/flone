package org.hermez.member.service;

import org.hm.member.dto.MemberLoginRequest;
import org.hm.member.dto.MemberRegisterRequest;
import org.hm.member.model.Member;

public interface MemberService {
   void registerMember(MemberRegisterRequest memberRegisterRequest);
   Member loginMember(MemberLoginRequest memberLoginRequest);
}
