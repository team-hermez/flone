package org.hermez.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.hermez.member.dto.MemberLoginRequest;
import org.hermez.member.dto.MemberRegisterRequest;
import org.hermez.member.dto.MyAccountEditRequest;
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

    //TODO 회원가입 유효성 비밀번호 서비스에서 처리 및 버튼? 하나 만들어서 누르면 비번 보이도록 ex) **** -> 1234
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

    //TODO 로그인 유효성 이메일, 비밀번호
//    @PostMapping("login1.hm")
//    public String postLogin(@Validated @ModelAttribute("member") MemberLoginRequest memberLoginRequest,
//                            BindingResult bindingResult, HttpSession session, Model model) {
//        // 로그인 시 아이디가 존재하는지 확인
//        Member member = memberService.findMemberByEmail(memberLoginRequest.getEmail());
//        if (member == null) {
//            bindingResult.rejectValue("email", "email.notfound", "아이디가 없습니다.");
//            return "flone/login";
//        }
//
//        // 비밀번호 확인
//        boolean isPasswordValid = memberService.checkPassword(memberLoginRequest.getEmail(), memberLoginRequest.getPassword());
//        if (!isPasswordValid) {
//            bindingResult.rejectValue("password", "password.incorrect", "비밀번호가 틀립니다.");
//            return "flone/login";
//        }
//
//        // 로그인 성공
//        session.setAttribute("MEMBER", member);
//        return "redirect:/flone/index.hm";
//    }
//}

    @PostMapping("login.hm")
    public String postLogin(@Validated @ModelAttribute("member") MemberLoginRequest memberLoginRequest, HttpSession session) {
        System.out.println("postLogin member start.");
        memberService.loginMember(memberLoginRequest);
        Member member1 = (Member) session.getAttribute("MEMBER");
        System.out.println("postLogin member1 session = " + member1.getName());

        return "redirect:/flone/index.hm";
    }

    @GetMapping("naverlogin.hm")
    public String naverLogin(HttpSession session) {
        try {
            String redirectURI = URLEncoder.encode("http://localhost:8080/flone/member/naverAuth.hm", "UTF-8");

            SecureRandom random = new SecureRandom();
            String state = new BigInteger(130, random).toString(); // CSRF 방지를 위한 state 값

            String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
                    + "&client_id=" + NAVER_CLIENTID
                    + "&redirect_uri=" + redirectURI
                    + "&state=" + state;

            session.setAttribute("state1", state);

            return "redirect:" + apiURL; // 네이버 로그인 페이지로 리다이렉트

        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 에러 발생 시 에러 페이지로 이동
        }
    }
    @GetMapping("naverAuth.hm")
    public String naverAuth(@RequestParam("code") String code,
                            @RequestParam("state") String state,
                            HttpSession session) {
        try {
            String sessionState = (String) session.getAttribute("state1");
            if (sessionState.equals(state)) {
                String redirectURI = URLEncoder.encode("http://localhost:8080/flone/member/naverAuth.hm", "UTF-8");

                String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                        + "&client_id=" + NAVER_CLIENTID
                        + "&client_secret=" + NAVER_SECRET
                        + "&redirect_uri=" + redirectURI
                        + "&code=" + code
                        + "&state=" + state;

                StringBuilder res = new StringBuilder();
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    res.append(inputLine);
                }
                br.close();

                JSONObject jsonObj = new JSONObject(res.toString());
                String accessToken = jsonObj.getString("access_token");
                String refreshToken = jsonObj.getString("refresh_token");

                session.setAttribute("accessToken", accessToken);
                session.setAttribute("refreshToken", refreshToken);

                return "redirect:/flone/아직 구현 중입니다.hm";

            } else {
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }




}