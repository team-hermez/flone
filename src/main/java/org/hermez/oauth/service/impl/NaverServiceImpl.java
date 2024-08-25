package org.hermez.oauth.service.impl;

import org.hermez.instructor.service.InstructorService;
import org.hermez.member.exception.MemberServiceException;
import org.hermez.oauth.mapper.NaverMapper;
import org.hermez.oauth.service.NaverService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

import static org.springframework.web.context.request.RequestContextHolder.*;

@Service
public class NaverServiceImpl implements NaverService {
    private final NaverMapper naverMapper;
    private final InstructorService instructorService;

    @Value("${NAVER_CLIENTID}")
    private String NAVER_CLIENTID;

    @Value("${NAVER_SECRET}")
    private String NAVER_SECRET;

    public NaverServiceImpl(NaverMapper naverMapper, InstructorService instructorService) {
        this.naverMapper = naverMapper;
        this.instructorService = instructorService;
    }

    public String naverLogin(HttpSession session) {
        try {
            String redirectURI = URLEncoder.encode("http://localhost:8080/flone/oauth/naverHandle.hm", "UTF-8");

            SecureRandom random = new SecureRandom();
            String state = new BigInteger(130, random).toString(); // CSRF 방지를 위한 state 값

            String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
                    + "&client_id=" + NAVER_CLIENTID
                    + "&redirect_uri=" + redirectURI
                    + "&state=" + state;

            session.setAttribute("state1", state);

            return apiURL; // 네이버 로그인 페이지로 리다이렉트할 URL 반환
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 에러 발생 시 에러 페이지로 이동
        }
    }

    @Override
    public String naverHandle(String code, String state) {
        HttpServletRequest request = ((ServletRequestAttributes) currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        try {
            String sessionState = (String) session.getAttribute("state1");
            if (sessionState != null && sessionState.equals(state)) {
                String redirectURI = URLEncoder.encode("http://localhost:8080/flone/oauth/naverHandle.hm", "UTF-8");

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

//                System.out.println("selectNaverRefreshToken(refreshToken) = " + selectNaverRefreshToken(refreshToken));
//
//                if (selectNaverRefreshToken(refreshToken)) {
//                    MemberLoginResponse memberLoginResponse = naverOauthMapper.selectNaverlogin(refreshToken);
//
//                    Instructor instructor = instructorService.findByMemberLoginResponseId(memberLoginResponse);
//                    Admin admin = new Admin();
//
//                    if (memberLoginResponse.getRoleId() == 1) {
//                        session.setAttribute("MEMBER", memberLoginResponse);
//                    } else if (memberLoginResponse.getRoleId() == 2) {
//                        session.setAttribute("MEMBER", memberLoginResponse);
//                        session.setAttribute("INSTRUCTOR", instructor);
//                    } else if (memberLoginResponse.getRoleId() == 3) {
//                        session.setAttribute("MEMBER", memberLoginResponse);
//                        session.setAttribute("INSTRUCTOR", instructor);
//                        session.setAttribute("ADMIN", admin);
//                    }
//                    return "redirect:/flone/index.hm";
//                } else if (!selectNaverRefreshToken(refreshToken)) {
                    return "redirect:/flone/member/register.hm"; //데베에 없으면 회원가입으로 가도록.
//                }
            }
        } catch (Exception e) {
            throw new MemberServiceException("네이버 로그인 중 오류가 발생했습니다.", e);
        }
        return "error";
    }


    @Override
    public boolean selectNaverRefreshToken(String refreshToken) {
        return selectNaverRefreshToken(refreshToken);
    }
}
