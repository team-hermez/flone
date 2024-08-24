package org.hermez.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.hermez.admin.model.Admin;
import org.hermez.admin.service.AdminService;
import org.hermez.instructor.model.Instructor;
import org.hermez.instructor.service.InstructorService;
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
    private final InstructorService instructorService;

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
        Instructor instructor = instructorService.findByMemberId(member.getMemberId());
        //TODO admin 정보 받아오기
        Admin admin = new Admin();

        if (member.getRoleId() == 1) {
            session.setAttribute("MEMBER", member);
        }
        if (member.getRoleId() == 2) {
            session.setAttribute("MEMBER", member);
            session.setAttribute("INSTRUCTOR", instructor);
        } else if (member.getRoleId() == 3) {
            session.setAttribute("MEMBER", member);
            session.setAttribute("INSTRUCTOR", instructor);
            session.setAttribute("Admin", admin);
        }
    }

    @Override
    public void save(Member member) {
        memberMapper.registerMember(member);
    }
}
