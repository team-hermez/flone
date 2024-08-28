package org.hermez.oauth.controller;

import org.hermez.oauth.service.SocialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 소셜 로그인을 처리하는 컨트롤러.
 *
 * 네이버 로그인을 시작하고, 로그인 콜백을 처리하며, 로그인 후 사용자 프로필을 조회하는 기능을 제공합니다.
 *
 * @author 김다은
 */
@Controller
@RequestMapping("flone/oauth")
public class SocialController {

    private final SocialService socialService;

    /**
     * 생성자
     *
     * @param socialService 소셜 로그인 서비스
     */
    public SocialController(SocialService socialService) {
        this.socialService = socialService;
    }

    /**
     * 네이버 로그인 페이지로 리다이렉트합니다.
     *
     * 소셜 서비스에서 제공하는 네이버 로그인 URL로 리다이렉트하여 로그인 프로세스를 시작합니다.
     *
     * @param session HTTP 세션
     * @return 네이버 로그인 페이지로 리다이렉트하는 URL
     */
    @GetMapping("naverlogin.hm")
    public String naverLogin(HttpSession session) {
        try {
            String apiURL = socialService.naverLogin(session);
            return "redirect:" + apiURL; // 네이버 로그인 페이지로 리다이렉트
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 에러 발생 시 에러 페이지로 이동
        }
    }

    /**
     * 네이버 로그인 후 콜백을 처리합니다.
     *
     * 네이버에서 전달한 인증 코드와 상태를 처리하여 로그인 후 사용자 정보를 얻습니다.
     *
     * @param code 인증 코드
     * @param state 상태 값
     * @return 로그인 처리 후 결과를 반환하는 URL
     */
    @GetMapping("naverHandle.hm")
    public String naverHandle(@RequestParam("code") String code, @RequestParam("state") String state) {
        return socialService.naverHandle(code, state);
    }

    /**
     * 로그인한 사용자의 프로필을 조회합니다.
     *
     * 소셜 로그인 후 세션에 저장된 정보를 기반으로 사용자 프로필을 조회합니다.
     *
     * @param session HTTP 세션
     * @return 사용자 프로필을 조회하는 결과를 반환하는 URL
     */
    @GetMapping("profile.hm")
    public String getMemberProfile(HttpSession session) {
        return socialService.naverGetProfile(session);
    }

}
