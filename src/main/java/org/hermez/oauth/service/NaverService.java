package org.hermez.oauth.service;

import javax.servlet.http.HttpSession;

public interface NaverService {
    String naverHandle(String code, String state);
    String naverLogin(HttpSession session);
    boolean selectNaverRefreshToken(String refreshToken);
}
