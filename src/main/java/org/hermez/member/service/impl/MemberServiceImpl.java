package org.hermez.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.hermez.member.dto.MemberLoginRequest;
import org.hermez.member.dto.MemberRegisterRequest;
import org.hermez.member.mapper.MemberMapper;
import org.hermez.member.model.Member;

import org.hermez.member.service.MemberService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final HttpSession session;

    @Override
    public void registerMember(MemberRegisterRequest memberRegisterRequest) {
        Member member = new Member(
                memberRegisterRequest.getRoleId(),
                memberRegisterRequest.getEmail(),
                memberRegisterRequest.getPassword(),
                memberRegisterRequest.getName(),
                memberRegisterRequest.getPhone(),
                memberRegisterRequest.getCreatedAt()
        );
        save(member);
    }

    @Override
    public void loginMember(MemberLoginRequest memberLoginRequest) {
        Member member = memberMapper.loginMember(memberLoginRequest);

        if(member.getRoleId() == 1){
            session.setAttribute("MEMBER", member);
        } else if(member.getRoleId() == 2){
            // 강사 = 강사 서비스 select 강사정보 가져옴 (member.getMemberId)
//                session.setAttribute("INSTRUCTOR", instructor);
        } else if(member.getRoleId() == 3){
            session.setAttribute("MEMBER", member);
//                session.setAttribute("INSTRUCTOR", instructor);
//                session.setAttribute("ADMIN", admin);
        }
        return ;
    }

    @Override
    public void save(Member member) {
        memberMapper.registerMember(member);
    }


}
