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

/**
 * 회원 관련시 사용되는 서비스 로직입니다.
 *
 * @author 김다은
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final HttpSession session;
    private final InstructorService instructorService;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 회원을 등록합니다.
     *
     * 이메일과 전화번호 중복 여부를 검사하고, 소셜 로그인 ID가 있는 경우와 없는 경우를 처리합니다.
     * 비밀번호는 인코딩되어 저장됩니다.
     *
     * @param memberRegisterRequest 회원 등록 요청 데이터
     * @throws MemberServiceException 중복된 이메일 또는 전화번호가 존재할 경우, 또는 오류가 발생한 경우
     */
    public void registerMember(MemberRegisterRequest memberRegisterRequest) {
        try {
            int emailExist = memberMapper.selectMemberRegisterEmailExist(memberRegisterRequest);
            int phoneExist = memberMapper.selectMemberPhoneExist(memberRegisterRequest);

            if (emailExist == 1) {
                throw new MemberServiceException("중복된 이메일이 존재합니다.");
            }

            if (phoneExist == 1) {
                throw new MemberServiceException("중복된 전화번호가 존재합니다.");
            }

            if (session != null) {
                String socialId = (String) session.getAttribute("socialLoginId");
                Member member = null;
                if (socialId != null) {
                    String encodedPassword = passwordEncoder.encode(memberRegisterRequest.getEncodedPassword());
                    MemberSocialLoginResponse memberSocialLoginResponse = new MemberSocialLoginResponse(
                            memberRegisterRequest.getRoleId(),
                            memberRegisterRequest.getName(),
                            socialId,
                            memberRegisterRequest.getEmail(),
                            encodedPassword,
                            memberRegisterRequest.getPhone(),
                            memberRegisterRequest.getMemberStatus(),
                            memberRegisterRequest.getCreatedAt()
                    );
                    memberMapper.updateSocialMember(memberSocialLoginResponse);
                } else {
                    String encodedPassword = passwordEncoder.encode(memberRegisterRequest.getEncodedPassword());
                    member = new Member(
                            memberRegisterRequest.getRoleId(),
                            memberRegisterRequest.getName(),
                            memberRegisterRequest.getEmail(),
                            encodedPassword,
                            memberRegisterRequest.getPhone(),
                            memberRegisterRequest.getMemberStatus(),
                            memberRegisterRequest.getCreatedAt()
                    );
                    save(member);
                }
            }
        } catch (MemberServiceException e) {
            throw new MemberServiceException(e.getMessage());
        } catch (Exception e) {
            throw new MemberServiceException("회원가입 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 회원 로그인 기능을 처리합니다.
     *
     * 이메일 존재 여부 및 회원 상태를 확인하고, 비밀번호를 비교하여 로그인을 처리합니다.
     * 로그인 성공 시, 역할에 따라 세션을 설정합니다.
     *
     * @param memberLoginRequest 회원 로그인 요청 데이터
     * @throws MemberServiceException 이메일이 없거나 비밀번호가 맞지 않는 경우, 회원 상태가 비활성인 경우
     */
    @Override
    public void loginMember(MemberLoginRequest memberLoginRequest) {
        try {
            int exists = memberMapper.selectMemberEmailExist(memberLoginRequest);
            int status = memberMapper.selectMemberStatus(memberLoginRequest);

            if (exists == 0) {
                throw new MemberServiceException("등록된 이메일이 없습니다.");
            }

            if (status == 1) {
                throw new MemberServiceException("비회원입니다. 관리자에게 문의해주세요.");
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
        } catch (MemberServiceException e) {
            throw new MemberServiceException(e.getMessage());
        } catch (Exception e) {
            throw new MemberServiceException("로그인 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 소셜 로그인 회원의 정보를 업데이트합니다.
     *
     * @param memberSocialLoginResponse 소셜 로그인 회원의 정보
     */
    public void update(MemberSocialLoginResponse memberSocialLoginResponse) {
        memberMapper.updateSocialMember(memberSocialLoginResponse);
    }

    /**
     * 회원 정보를 저장합니다.
     *
     * @param member 저장할 회원 정보
     */
    @Override
    public void save(Member member) {
        memberMapper.registerMember(member);
    }

    /**
     * 회원 상태를 업데이트합니다.
     *
     * @param memberId 회원 ID
     */
    @Override
    public void insertMemberStatus(int memberId) {
        memberMapper.insertMemberStatus(memberId);
    }

    /**
     * 회원의 정보를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return 회원 정보
     */
    @Override
    public MyAccountResponse getMyAccount(int memberId) {
        return memberMapper.getMyAccount(memberId);
    }

    /**
     * 회원 정보를 수정합니다.
     *
     * 전화번호 중복을 확인하고, 비밀번호를 인코딩하여 업데이트합니다.
     *
     * @param myAccountEditRequest 수정할 회원 정보
     * @throws MemberServiceException 전화번호 중복 시 오류 발생
     */
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

    /**
     * 비밀번호를 변경합니다.
     *
     * 비밀번호 확인을 위해 이메일과 전화번호를 검사하고, 일치하면 비밀번호를 변경합니다.
     *
     * @param memberCheckPasswordRequest 비밀번호 변경 요청 데이터
     * @return 비밀번호 변경 성공 시 리다이렉션 URL
     * @throws MemberServiceException 비밀번호 정보가 일치하지 않을 경우, 또는 오류가 발생한 경우
     */
    @Override
    public String changePassword(MemberCheckPasswordRequest memberCheckPasswordRequest) {
        try {
            int check = memberMapper.selectCheckPassword(memberCheckPasswordRequest);
            if (check == 1) {
                String encodedPassword = passwordEncoder.encode(memberCheckPasswordRequest.getPasswordConfirm());
                memberCheckPasswordRequest.setPasswordConfirm(encodedPassword);
                memberMapper.changePassword(memberCheckPasswordRequest);
                return "redirect:/flone/login.hm";
            } else {
                throw new MemberServiceException("정보가 일치하지않습니다.");
            }
        } catch (MemberServiceException e) {
            throw new MemberServiceException(e.getMessage());
        } catch (Exception e) {
            throw new MemberServiceException("비밀번호 변경 중 오류가 발생했습니다.");
        }
    }
}
