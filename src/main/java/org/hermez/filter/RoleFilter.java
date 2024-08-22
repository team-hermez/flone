//package org.hermez.filter;
//import org.hermez.member.dto.MemberLoginRequest;
//import org.hermez.member.model.Member;
//import org.hermez.member.service.MemberService;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Component
//@WebFilter(urlPatterns = "/*")
//public class RoleFilter implements Filter {
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
//        HttpSession session = httpRequest.getSession(false); //세션이 존재하면 현재 sesssion 반환, 없다면 null을 반환
//
//        String email = httpRequest.getParameter("email");
//        String password = httpRequest.getParameter("password");
//        System.out.println("doFilter email = " + email);
//        System.out.println("password = " + password);
//
//                if("POST".equals(httpRequest.getMethod()) && "/flone/member/login.hm".equals(httpRequest.getRequestURI())) {
//                    if (session != null) {
//                        MemberLoginRequest memberLoginRequest = new MemberLoginRequest(email, password);
//                        System.out.println("if pass, memberLoginRequest = " + memberLoginRequest);
//                        chain.doFilter(request, response);
//                        Member member = (Member) session.getAttribute("MEMBER");
//
//
//                        System.out.println("Filter member.getRoleId() = " + member.getRoleId()); //1 나옴 ㅎㅎ
//
//                        if (member.getRoleId() == 1) { // 학생
//                            httpResponse.sendRedirect("/flone/index.hm");
//                            chain.doFilter(request, response);
//                        } else if (member.getRoleId() == 2) { //강사
//                            httpResponse.sendRedirect("/flone/index.hm");
//                            chain.doFilter(request, response);
//                        } else if (member.getRoleId() == 3) { //어드민
//                            httpResponse.sendRedirect("/flone/index.hm");
//                            chain.doFilter(request, response);
//                        } else {
//                            System.out.println("Nosession 4.");
//                            httpResponse.sendRedirect("/flone/index.hm");
//                            return;
//                        }
//                    }
//                }
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
