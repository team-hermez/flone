package org.hermez.filter;

import lombok.extern.log4j.Log4j;
import org.hermez.filter.exception.AdminFilterException;
import org.hermez.member.dto.MemberLoginResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 요청에 대한 역할 기반 접근 제어를 수행하는 필터입니다. 이 필터는 특정 경로에 대해
 * 접근을 허용하고, 로그인된 사용자와 그 역할에 따라 다른 페이지로 리디렉션합니다.
 *
 * @author 김혁진
 */
@Log4j
@Component
public class RoleFilter implements Filter {

    /**
     * 화이트리스트 경로 배열입니다. 이 배열에 정의된 경로는 필터링을 적용하지 않습니다.
     */
    private final String[] whitelist = {
            "/",
            "/flone/index.hm",
            "/flone/member/login.hm",
            "/flone/member/register.hm",
            "/flone/course/list.hm",
            "/flone/instructor/list.hm",
            "/flone/instructor/detail.hm",
            "/flone/oauth/naverlogin.hm",
            "/flone/member/updatepassword.hm",
            "/flone/member/changepassword.hm",
            "/flone/oauth/naverHandle.hm",
            "/flone/oauth/profile.hm",
            "/flone/course/detail.hm",
            "/assets/*",
            "*.js",
            "*.css",
            "*.png",
            "*.jpg",
            "*.jpeg",
            "*.gif",
            "*.ico",
            "error.jsp",
            "error"
    };

    /**
     * 필터 초기화 메서드입니다. 현재 구현에서는 초기화 작업을 수행하지 않습니다.
     *
     * @param filterConfig 필터 구성 정보
     * @throws ServletException 초기화 중 발생할 수 있는 예외
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 요청을 필터링하고, 화이트리스트와 역할에 따라 접근을 제어합니다.
     *
     * @param request 요청 객체
     * @param response 응답 객체
     * @param chain 필터 체인
     * @throws IOException 입출력 예외
     * @throws ServletException 서블릿 예외
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        if (isWhitelist(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("MEMBER") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/flone/member/login.hm");
            return;
        }

        MemberLoginResponse member = (MemberLoginResponse) session.getAttribute("MEMBER");

        int roleId = member.getRoleId();
        if ((roleId == 1 || roleId == 2) && requestURI.startsWith("/flone/admin")) {
            throw new AdminFilterException("관리자만 들어갈 수 있습니다.");
        }
        chain.doFilter(request, response);
    }

    /**
     * 요청 URI가 화이트리스트에 포함되는지 확인합니다.
     *
     * @param requestURI 요청 URI
     * @return 화이트리스트에 포함되면 true, 그렇지 않으면 false
     */
    private boolean isWhitelist(String requestURI) {
        return PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }

    /**
     * 필터 종료 메서드입니다. 현재 구현에서는 종료 작업을 수행하지 않습니다.
     */
    @Override
    public void destroy() {
    }
}
