package org.hermez.instructor.controller;

import org.hermez.common.page.Page;
import org.hermez.instructor.dto.InstructorDetailResponse;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.dto.InstructorRegisterRequest;
import org.hermez.instructor.service.InstructorService;
import org.hermez.member.dto.MemberLoginResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 강사 관련 요청을 처리하는 컨트롤러 클래스입니다.
 *
 * @author 김다은
 */
@Controller
@RequestMapping("flone/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    /**
     * 생성자 주입을 통한 InstructorService 설정.
     *
     * @param instructorService 강사 관련 비즈니스 로직을 처리하는 서비스 객체
     */
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    /**
     * 강사 목록 페이지를 조회합니다.
     *
     * @param page 조회할 페이지 번호 (기본값: 1)
     * @param model 뷰에 전달할 데이터 모델
     * @return 강사 목록을 표시할 뷰의 이름
     */
    @GetMapping("list.hm")
    public String getInstructorList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {
        Page<InstructorListResponse> instructorList = instructorService.selectInstructorList(page);
        model.addAttribute("instructors", instructorList);
        return "flone/instructor-list";
    }

    /**
     * 강사 등록 페이지를 반환합니다.
     *
     * @return 강사 등록 페이지의 뷰 이름
     */
    @GetMapping("register.hm")
    public String getInstructorRegisterPage() {
        return "flone/instructor-register";
    }

    /**
     * 특정 강사의 상세 정보를 조회합니다.
     *
     * @param instructorId 조회할 강사의 ID
     * @param model 뷰에 전달할 데이터 모델
     * @return 강사 상세 정보를 표시할 뷰의 이름
     */
    @GetMapping("detail.hm")
    public String getInstructorDetailPage(@RequestParam int instructorId, Model model) {
        InstructorDetailResponse instructorDetailResponses = instructorService.selectInstructorDetail(instructorId);
        model.addAttribute("instructorDetail", instructorDetailResponses);
        return "flone/instructor-detail";
    }

    /**
     * 강사 등록 요청을 처리합니다.
     *
     * @param instructorRegisterRequest 강사 등록 요청 데이터
     * @param httpRequest HTTP 요청 객체
     * @return 강사 목록 페이지로 리다이렉트
     */
    @PostMapping("regist.hm")
    public String postInstructorRegister(InstructorRegisterRequest instructorRegisterRequest, HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        MemberLoginResponse member = (MemberLoginResponse) session.getAttribute("MEMBER");
        instructorRegisterRequest.setMemberId(member.getMemberId());
        instructorService.insertInstructor(instructorRegisterRequest);
        return "redirect:/flone/instructor/list.hm";
    }
}
