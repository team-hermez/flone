package org.hermez.course.controller;

import org.hermez.common.page.Page;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.dto.CourseListResponse;
import org.hermez.course.dto.CourseRegisterRequest;
import org.hermez.course.model.CourseTime;
import org.hermez.course.service.CourseService;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.model.Instructor;
import org.hermez.member.dto.MemberLoginResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 강의 관련 요청을 처리하는 클래스입니다.
 *
 * @author 엄진수
 */
@Controller
@RequestMapping("flone/course")
public class CourseController {
    private final CourseService courseService;
    private final ServletRequest httpServletRequest;
    private final HttpSession httpSession;
    private CourseRegisterRequest courseRegisterRequest;
    private CourseTime courseTime;
    /**
     * CourseController 생성자입니다.
     *
     * @param courseService
     */
    public CourseController(CourseService courseService, @Qualifier("httpServletRequest") ServletRequest httpServletRequest, HttpSession httpSession) {
        this.courseService = courseService;
        this.httpServletRequest = httpServletRequest;
        this.httpSession = httpSession;
    }

    /**
     * 전체 강의 목록을 조회합니다.
     *
     * @param model 모델 객체
     * @param page  현재 page
     * @return 게시판 목록 페이지 뷰 이름
     */

    @GetMapping(value = "list.hm")
    public String getCourseListByCategory(@RequestParam (name ="category", defaultValue = "default") String category,
                                          @RequestParam(name ="subject", required = false) String subject,
                                          @RequestParam (name ="name", required = false) String instructorName,
                                          @RequestParam (name ="grade", required = false) String grade,
                                          @RequestParam (value = "page", defaultValue = "1") int page,
                                          Model model){


        if (category.equals("default")) {
            Page<CourseListResponse> courseList = courseService.getCourseList(page);
            model.addAttribute("courses", courseList);
            return "flone/course-list";
        }else {
            Page<CourseListResponse> courseList
                    = courseService.getCourseListByCategory(category, subject, instructorName, grade, page);
            model.addAttribute("courses", courseList);
            return "flone/course-list";
        }
    }

    /**
     * 강의 상세 페이지 목록을 조회합니다.
     *
     * @param model 모델 객체
     * @param courseId  course의 pk
     * @return 상세 페이지 뷰 이름
     */
    @GetMapping(value = "detail.hm")
    public String getCourseDetailPage(Model model, @RequestParam int courseId) {
        CourseDetailResponse courseDetailList = courseService.courseDetailService(courseId);
        List<CourseTime> courseTimeList = courseService.courseDetailTime(courseId);
        List<CourseListResponse> courseListByInstructorName = courseService.getCourseListByInstructor(courseDetailList.getInstructorName());

        model.addAttribute("courseDetail", courseDetailList);
        model.addAttribute("courseTime", courseTimeList);
        model.addAttribute("courseByInstructor", courseListByInstructorName);

        return "flone/course-detail";
    }

    /**
     * 강의 등록 페이지로 연결해주는 콘트롤러입니다.
     * @param model 모델 객체
     * @param request 세션
     * @return 강의 등록 페이지 뷰 이름
     */
    @GetMapping(value = "register.hm")
    public String getCourseRegisterPage(Model model, final HttpServletRequest request) {
        return "flone/course-register";
    }

    /**
     * 새로운 강의를 등록합니다
     * @param courseRegisterRequest 강의 등록 정보
     * @return 강의 리스트로 redirect
     */
    @PostMapping("regist.hm")
    public String postCourseRegister(@ModelAttribute CourseRegisterRequest courseRegisterRequest) {
        MemberLoginResponse memberLoginResponse = (MemberLoginResponse) httpSession.getAttribute("MEMBER");
        int memberId = memberLoginResponse.getMemberId();
        int instructorId = courseService.getInstructorIdByMemberId(memberId);

        courseRegisterRequest.setInstructorId(instructorId);
        courseService.insertCourse(courseRegisterRequest);
        return "redirect:list.hm";
    }

}
