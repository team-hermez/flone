package org.hermez.oauth.service.impl;

import org.hermez.instructor.service.InstructorService;
import org.hermez.member.exception.MemberServiceException;
import org.hermez.member.service.MemberService;
import org.hermez.oauth.exception.OauthServiceException;
import org.hermez.oauth.mapper.SocialMapper;
import org.hermez.oauth.service.SocialService;
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

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Service
public class SocialServiceImpl implements SocialService {
    private final SocialMapper socialMapper;
    private final InstructorService instructorService;
    private final MemberService memberService;
    private final String API_URL = "https://openapi.naver.com/v1/nid/me";

    @Value("${NAVER_CLIENTID}")
    private String NAVER_CLIENTID;

    @Value("${NAVER_SECRET}")
    private String NAVER_SECRET;

    public SocialServiceImpl(SocialMapper socialMapper, InstructorService instructorService, MemberService memberService) {
        this.socialMapper = socialMapper;
        this.instructorService = instructorService;
        this.memberService = memberService;
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

                return naverGetProfile(session);
            }
        } catch (OauthServiceException e) {
            throw new OauthServiceException(e.getMessage());
        } catch (Exception e) {
            throw new MemberServiceException("네이버 로그인 중 오류가 발생했습니다.", e);
        }
        return "error";
    }

    @Override
    public String naverGetProfile(HttpSession session) {
        try {
            URL url = new URL("https://openapi.naver.com/v1/nid/me");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + session.getAttribute("accessToken"));

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            System.out.println("JSON Response: " + jsonResponse.toString(2)); // Pretty print JSON

            JSONObject responseObject = jsonResponse.getJSONObject("response");
            String id = responseObject.getString("id");
            String name = responseObject.getString("name");
            String email = responseObject.getString("email");

            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);

            return "redirect:/flone/index.hm";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}




//    @Override
//    public boolean selectNaverSocialId(String socialLoginId) {
//        boolean exist = socialMapper.selectNaverSocialId(socialLoginId);
//        return exist;
//    }
