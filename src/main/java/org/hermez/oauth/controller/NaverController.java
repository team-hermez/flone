package org.hermez.oauth.controller;

import org.hermez.oauth.service.NaverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("flone/oauth")
public class NaverController {
    private final NaverService naverOauthService;

    public NaverController(NaverService naverOauthService) {
        this.naverOauthService = naverOauthService;
    }

    @GetMapping("naverlogin.hm")
    public String naverLogin(HttpSession session) {
        try {
            String apiURL = naverOauthService.naverLogin(session);
            return "redirect:" + apiURL; // 네이버 로그인 페이지로 리다이렉트
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 에러 발생 시 에러 페이지로 이동
        }
    }

    @GetMapping("naverHandle.hm")
    public String naverHandle(@RequestParam("code") String code, @RequestParam("state") String state) {
        return naverOauthService.naverHandle(code, state);
    }
}
