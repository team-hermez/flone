//package org.hermez.filter;
//
//import lombok.extern.log4j.Log4j;
//import org.hermez.filter.exception.FilterException;
//import org.hermez.member.exception.MemberServiceException;
//import org.hermez.member.model.Member;
//import org.hermez.member.service.MemberService;
//import org.springframework.stereotype.Component;
//import org.springframework.util.PatternMatchUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Log4j
//@Component
//public class RoleFilter implements Filter {
//    private final String[] whitelist = {"/", "/flone/index.hm", "/flone/member/login.hm", "/flone/member/register.hm", "/assets/*"};
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        MemberService memberService;
//        System.out.println("Filter init");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        String requestURI = httpRequest.getRequestURI();
//
//        System.out.println("requestURI@@@@@@@@ = " + requestURI);
//
//        try {
//            if (isLoginCheckPath(requestURI)) {
//                HttpSession session = httpRequest.getSession(false); //세션이 존재하면 현재 sesssion 반환, 없다면 null을 반환
//                if (session == null || session.getAttribute("MEMBER") == null) {
//                    httpResponse.sendRedirect("/flone/member/register.hm");
//                    throw new FilterException("등록되지 않은 회원입니다.");
//                }
//
//                chain.doFilter(request, response);
//                Member member = (Member) session.getAttribute("MEMBER");
//                if (member.getRoleId() == 1) { // 학생
//                    httpResponse.sendRedirect("/flone/index.hm");
//                    throw new MemberServiceException("학생은 이용할 수 없습니다.");
//                } else if (member.getRoleId() == 2) { //강사
//                    httpResponse.sendRedirect("/flone/index.hm");
//                    throw new MemberServiceException("강사는 이용할 수 없습니다.");
//                } else if (member.getRoleId() == 3) { //어드민
//                    httpResponse.sendRedirect("/flone/index.hm");
//                    throw new MemberServiceException("어드민은 이용할 수 없습니다.");
//                }
//                chain.doFilter(request, response);
//            }
//            chain.doFilter(request, response);
//        }catch (FilterException e) {
//            throw new FilterException(e.getMessage());
//        }
//        catch (Exception e) {
//            throw new FilterException("필터 처리 중 오류가 발생했습니다.", e);
//        }
//    }
//
//    private boolean isLoginCheckPath(String requestURI) {
//        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//}
//
//
