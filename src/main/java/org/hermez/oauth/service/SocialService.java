package org.hermez.oauth.service;

import javax.servlet.http.HttpSession;

public interface SocialService {
    String naverHandle(String code, String state);
    String naverLogin(HttpSession session);

    String naverGetProfile(HttpSession session);

//    boolean selectNaverSocialId(String socialLoginId);
}
