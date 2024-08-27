package org.hermez.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.hermez.instructor.model.Instructor;
import org.hermez.instructor.service.InstructorService;
import org.hermez.member.dto.*;
import org.hermez.member.exception.MemberServiceException;
import org.hermez.member.mapper.MemberMapper;
import org.hermez.member.model.Member;

import org.hermez.member.service.MemberService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final HttpSession session;
    private final InstructorService instructorService;
    private final BCryptPasswordEncoder passwordEncoder;

    public void registerMember(MemberRegisterRequest memberRegisterRequest) {
        boolean emailExist = memberMapper.selectMemberRegisterEmailExist(memberRegisterRequest);
        boolean phoneExist = memberMapper.selectMemberPhoneExist(memberRegisterRequest);

        if (emailExist) {
            throw new MemberServiceException("중복된 이메일이 존재합니다.");
        }

        if (phoneExist) {
            throw new MemberServiceException("중복된 전화번호가 존재합니다.");
        }

        if(session!=null) {
            String socialLoginId = (String) session.getAttribute("socialLoginId");

            String encodedPassword = passwordEncoder.encode(memberRegisterRequest.getEncodedPassword());
            Member member = new Member(
                    memberRegisterRequest.getRoleId(),
                    memberRegisterRequest.getName(),
                    socialLoginId,
                    memberRegisterRequest.getEmail(),
                    encodedPassword,
                    memberRegisterRequest.getPhone(),
                    memberRegisterRequest.getMemberStatus(),
                    memberRegisterRequest.getCreatedAt()
            );
            save(member);
        }
    }

    @Override
    public void loginMember(MemberLoginRequest memberLoginRequest) {
        boolean exist = memberMapper.selectMemberEmailExist(memberLoginRequest);
        if (!exist) {
            throw new MemberServiceException("등록된 이메일이 없습니다.");
        }

        MemberLoginResponse memberLoginResponse = memberMapper.loginMember(memberLoginRequest);
        if (memberLoginResponse == null) {
            throw new MemberServiceException("비밀번호가 맞지 않습니다.");
        }

        boolean passwordsMatch = passwordEncoder.matches(memberLoginRequest.getPassword(), memberLoginResponse.getEncodedPassword());
        if (!passwordsMatch) {
            throw new MemberServiceException("비밀번호가 맞지 않습니다.");
        }

        Instructor instructor = instructorService.findByMemberLoginResponseId(memberLoginResponse);
        MemberLoginResponse admin = memberMapper.loginMember(memberLoginRequest);

        if (memberLoginResponse.getRoleId() == 1) {
            session.setAttribute("MEMBER", memberLoginResponse);
        } else if (memberLoginResponse.getRoleId() == 2) {
            session.setAttribute("MEMBER", memberLoginResponse);
            session.setAttribute("INSTRUCTOR", instructor);
        } else if (memberLoginResponse.getRoleId() == 3) {
            session.setAttribute("MEMBER", memberLoginResponse);
            session.setAttribute("INSTRUCTOR", instructor);
            session.setAttribute("ADMIN", admin);
        }
    }



    @Override
    public void save(Member member) {
        memberMapper.registerMember(member);
    }

    @Override
    public void insertMemberStatus(int memberId) {
        memberMapper.insertMemberStatus(memberId);
    }


    @Override
    public MyAccountResponse getMyAccount(int memberId) {
        MyAccountResponse myAccountResponse = memberMapper.getMyAccount(memberId);
        return myAccountResponse;
    }

    @Override
    public void updateMyAccount(MyAccountEditRequest myAccountEditRequest) {
        int selectPhoneExists = memberMapper.selectPhoneExists(myAccountEditRequest);
        try {
            String encodedPassword = passwordEncoder.encode(myAccountEditRequest.getPasswordConfirm());
            myAccountEditRequest.setPasswordConfirm(encodedPassword);
            if (selectPhoneExists == 0) {
                memberMapper.updateMyAccountPhone(myAccountEditRequest);
                memberMapper.updateMyAccountPassword(myAccountEditRequest);
            } else if (selectPhoneExists == 1) {
                throw new MemberServiceException("이미 존재하는 핸드폰 번호입니다.");
            }
        } catch (MemberServiceException e) {
            throw new MemberServiceException(e.getMessage());
        } catch (Exception e) {
            throw new MemberServiceException("내 정보 수정 중 오류가 발생했습니다.", e);
        }
    }
}
