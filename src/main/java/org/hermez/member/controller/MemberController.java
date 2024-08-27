package org.hermez.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.hermez.member.dto.MemberLoginRequest;
import org.hermez.member.dto.MemberRegisterRequest;
import org.hermez.member.dto.MyAccountEditRequest;
import org.hermez.member.dto.MyAccountResponse;
import org.hermez.member.model.Member;
import org.hermez.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("flone/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("login.hm")
    public String getMemberLoginPage(Model model) {
        return "flone/login";
    }

    @GetMapping("register.hm")
    public String getMemberRegisterPage(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("member", member);
        return "flone/register";
    }

    @GetMapping("logout.hm")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "flone/index";
    }

    @GetMapping("my-account.hm")
    public String getMyAccountPage(@RequestParam int memberId, Model model) {
        MyAccountResponse myAccountResponse = memberService.getMyAccount(memberId);
        model.addAttribute("myAccount", myAccountResponse);
        return "flone/my-account";
    }

    @PostMapping("my-account-edit.hm")
    public String postMyAccountEdit(@RequestParam int memberId, MyAccountEditRequest myAccountEditRequest) {
        myAccountEditRequest.setMemberId(memberId);
        memberService.updateMyAccount(myAccountEditRequest);
        return "redirect:my-account.hm?memberId=" + memberId;
    }

    @PostMapping("register.hm")
    public String postMemberRegister(@Validated @ModelAttribute("member") MemberRegisterRequest memberRegisterRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.reject("member", "member");
            return "flone/register";
        }
        memberService.registerMember(memberRegisterRequest);
        return "flone/login";
    }

    @PostMapping("login.hm")
    public String postLogin(@Validated @ModelAttribute("member") MemberLoginRequest memberLoginRequest, HttpSession session) {
        memberService.loginMember(memberLoginRequest);
        return "redirect:/flone/index.hm";
    }

    @PostMapping("delete.hm")
    public String postDeleteMember(@RequestParam int memberId, HttpSession session) {
        memberService.insertMemberStatus(memberId);
        session.invalidate();

        return "redirect:/flone/index.hm";
    }

}