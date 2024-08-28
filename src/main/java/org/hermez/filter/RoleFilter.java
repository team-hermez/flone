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
@Log4j
@Component
public class RoleFilter implements Filter {
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

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


    private boolean isWhitelist(String requestURI) {
        return PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }

    @Override
    public void destroy() {
    }
}
