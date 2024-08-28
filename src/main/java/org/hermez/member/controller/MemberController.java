package org.hermez.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.hermez.member.dto.*;
import org.hermez.member.model.Member;
import org.hermez.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 회원 관련 요청을 처리하는 컨트롤러 클래스입니다.
 *
 * @author 김다은
 */

@Slf4j
@Controller
@RequestMapping("flone/member")
public class MemberController {

    private final MemberService memberService;

    /***
     * MemberController 생성자입니다.
     * @param memberService 모델 객체
     */
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /***
     * 회원 로그인 폼을 반환합니다.
     *
     * @param model 모델 객체
     * @return
     */
    @GetMapping("login.hm")
    public String getMemberLoginPage(Model model) {
        return "flone/login";
    }

    /***
     * 회원가입 폼을 반환합니다.
     *
     * @param member
     * @param model
     * @return 회원가입 뷰
     */
    @GetMapping("register.hm")
    public String getMemberRegisterPage(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("member", member);
        return "flone/register";
    }

    /***
     * 세션을 초기화 시킵니다.
     * @param session
     * @return 메인 뷰
     */
    @GetMapping("logout.hm")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    /***
     * 마이페이지 폼을 조회합니다.
     *
     * @param memberId
     * @param model
     * @return 마이페이지 뷰
     */
    @GetMapping("my-account.hm")
    public String getMyAccountPage(@RequestParam int memberId, Model model) {
        MyAccountResponse myAccountResponse = memberService.getMyAccount(memberId);
        model.addAttribute("myAccount", myAccountResponse);
        return "flone/my-account";
    }

    /***
     * 비밀번호 찾기 폼을 조회합니다.
     *
     * @return 비밀번호 찾기 뷰
     */
    @GetMapping("updatepassword.hm")
    public String getUpdatePassword() {
        return "flone/password-edit-form";
    }


    /***
     *
     * 입력한 전화번호, 비밀번호를 수정합니다.
     *
     * @param memberId 회원의 고유 ID
     * @param myAccountEditRequest 내 정보 편집 정보
     * @return 마이페이지 뷰
     */
    @PostMapping("my-account-edit.hm")
    public String postMyAccountEdit(@RequestParam int memberId, MyAccountEditRequest myAccountEditRequest) {
        myAccountEditRequest.setMemberId(memberId);
        memberService.updateMyAccount(myAccountEditRequest);
        return "redirect:my-account.hm?memberId=" + memberId;
    }

    /***
     * 회원을 등록합니다.
     *
     * @param memberRegisterRequest 회원 등록 정보
     * @param bindingResult 유효성 처리
     * @param model 모델 객체
     * @return 로그인 뷰
     */
    @PostMapping("register.hm")
    public String postMemberRegister(@Validated @ModelAttribute("member") MemberRegisterRequest memberRegisterRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.reject("member", "member");
            return "flone/register";
        }
        memberService.registerMember(memberRegisterRequest);
        return "flone/login";
    }

    /***
     * 로그인을 진행하는 메서드입니다.
     *
     * @param memberLoginRequest 로그인 정보
     * @param session 현재 세션
     * @return 메인 뷰로
     */
    @PostMapping("login.hm")
    public String postLogin(@Validated @ModelAttribute("member") MemberLoginRequest memberLoginRequest, HttpSession session) {
        memberService.loginMember(memberLoginRequest);
        return "redirect:/";
    }

    /***
     * 비회원 상태로 전환해주는 메서드입니다.
     *
     * @param memberId 회원의 고유 ID
     * @param session 현재 세션
     * @return 세션 초기화 후 메인 뷰로
     */
    @PostMapping("delete.hm")
    public String postDeleteMember(@RequestParam int memberId, HttpSession session) {
        memberService.insertMemberStatus(memberId);
        session.invalidate();
        return "redirect:/";
    }

    /***
     * 회원이 일치하면 입력한 비밀번호로 변경해주는 메서드입니다.
     *
     * @param memberCheckPasswordRequest 바꿀 비밀번호 객체
     * @return
     */
    @PostMapping("changepassword.hm")
    public String postUpdatePassword(MemberCheckPasswordRequest memberCheckPasswordRequest) {
        memberService.changePassword(memberCheckPasswordRequest);
        return "redirect:/";
    }

}