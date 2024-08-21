package org.hermez.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.hermez.member.dto.MemberLoginRequest;
import org.hermez.member.dto.MemberRegisterRequest;
import org.hermez.member.model.Member;
import org.hermez.member.model.MemberRepository;
import org.hermez.member.service.MemberService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void registerMember(MemberRegisterRequest memberRegisterRequest) {
        System.out.println("member = " + memberRegisterRequest);
        System.out.println("memberRegisterRequest.getEmail() = " + memberRegisterRequest.getEmail());
        System.out.println("memberRegisterRequest.getPassword = " + memberRegisterRequest.getPassword());
        System.out.println("memberRegisterRequest.getRole = " + memberRegisterRequest.getRole_Id());
        System.out.println("memberRegisterRequest.getName = " + memberRegisterRequest.getName());

        Member member = new Member(
                memberRegisterRequest.getRole_Id(),
                memberRegisterRequest.getEmail(),
                memberRegisterRequest.getPassword(),
                memberRegisterRequest.getName(),
                memberRegisterRequest.getPhone()
        );

       memberRepository.save(member);
    }

    @Override
    public Member loginMember(MemberLoginRequest memberLoginRequest) {
        Member member = new Member(
                memberLoginRequest.getEmail(),
                memberLoginRequest.getPassword()
        );

        Member members = memberRepository.loginMember(member);
        System.out.println("loginMember = " + members);
        return members;
    }


}
