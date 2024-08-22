package org.hermez.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.hermez.member.dto.MemberLoginRequest;
import org.hermez.member.dto.MemberRegisterRequest;
import org.hermez.member.dto.MyAccountResponse;
import org.hermez.member.model.Member;
import org.hermez.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("member",member);
        return "flone/register";
    }

    @PostMapping("login.hm")
    public String postLogin(@Validated @ModelAttribute("member") MemberLoginRequest memberLoginRequest, HttpSession session) {
        //TODO 로그인한 사람이 강사면 강사세션으로 로그인 되도록 Filter 만들기
        System.out.println("postLogin member start.");
        memberService.loginMember(memberLoginRequest);
        Member member1 = (Member) session.getAttribute("MEMBER");
        System.out.println("postLogin member1 session = " + member1.getName());

        return "redirect:/flone/index.hm";
    }

    @PostMapping("logout.hm")
    public String postLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }

    @GetMapping("my-account.hm")
    public String getMyAccountPage(HttpSession session, Model model) {
        MyAccountResponse myAccount = new MyAccountResponse(
                "example@example.com",
                "아쉬운거지",
                "password123",
                "010-1234-5678",
                "1990-01-01"
        );
        model.addAttribute("account", myAccount);
        return "flone/my-account";
    }

    @PostMapping("register.hm")
        public String postMemberRegister(@Validated @ModelAttribute("member") MemberRegisterRequest memberRegisterRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            bindingResult.reject("member", "member");
            return "flone/register";
        } else if(!memberRegisterRequest.getPassword().equals((memberRegisterRequest.getPassword1()))){
            bindingResult.rejectValue("password", "password1", "패스워드가 일치하지 않습니다.");
            return "flone/register";
        }

        memberService.registerMember(memberRegisterRequest);
        return "flone/login";
    }
}
