package org.hermez.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.hermez.member.dto.MemberLoginRequest;
import org.hermez.member.dto.MemberRegisterRequest;
import org.hermez.member.dto.MyAccountResponse;
import org.hermez.member.model.Member;
import org.hermez.member.service.MemberService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

@Slf4j
@Controller
@RequestMapping("flone/member")
public class MemberController {

    private final MemberService memberService;

    @Value("${NAVER_CLIENTID}")
    private String NAVER_CLIENTID;

    @Value("${NAVER_SECRET}")
    private String NAVER_SECRET;

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
        if (bindingResult.hasErrors()) {
            bindingResult.reject("member", "member");
            return "flone/register";
        } else if (!memberRegisterRequest.getPassword().equals((memberRegisterRequest.getPasswordNot()))) {
            bindingResult.rejectValue("password", "passwordNot", "패스워드가 일치하지 않습니다.");
            return "flone/register";
        }

        memberService.registerMember(memberRegisterRequest);
        return "flone/login";
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


}