package org.hermez.oauth.service;

import javax.servlet.http.HttpSession;

/**
 * 소셜 로그인 관련 기능을 제공하는 서비스 인터페이스입니다.
 *
 * @author 김다은
 */
public interface SocialService {

    /**
     * 네이버 로그인 처리 후 리다이렉트될 URL을 생성합니다.
     *
     * @param code 네이버로부터 받은 인증 코드
     * @param state 네이버로부터 받은 상태 값
     * @return 네이버 로그인 페이지로 리다이렉트할 URL
     */
    String naverHandle(String code, String state);

    /**
     * 네이버 로그인 페이지로 리다이렉트할 URL을 생성합니다.
     *
     * @param session HTTP 세션
     * @return 네이버 로그인 페이지로 리다이렉트할 URL
     */
    String naverLogin(HttpSession session);

    /**
     * 네이버 소셜 로그인 사용자의 프로필 정보를 조회합니다.
     *
     * @param session HTTP 세션
     * @return 소셜 로그인 사용자의 프로필 정보
     */
    String naverGetProfile(HttpSession session);

    /**
     * 주어진 소셜 로그인 ID가 데이터베이스에 존재하는지 확인합니다.
     *
     * @param socialLoginId 소셜 로그인 ID
     * @return 해당 소셜 로그인 ID가 존재하면 1, 그렇지 않으면 0
     */
    int selectNaverSocialId(String socialLoginId);
}
