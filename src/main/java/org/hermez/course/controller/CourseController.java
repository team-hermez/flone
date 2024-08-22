package org.hermez.course.controller;

import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.dto.CourseListResponse;
import org.hermez.course.dto.CourseRegisterRequest;
import org.hermez.course.model.CourseTime;
import org.hermez.course.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private CourseRegisterRequest courseRegisterRequest;
    private CourseTime courseTime;
    /**
     * CourseController 생성자입니다.
     *
     * @param courseService
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 전체 강의 목록을 조회합니다.
     *
     * @param model 모델 객체
     * @return 게시판 목록 페이지 뷰 이름
     */
    @GetMapping(value = "list.hm")
    public String getCourseList(Model model) {
        List<CourseListResponse> courseList = courseService.courseListService();
        model.addAttribute("courses", courseList);
        return "flone/course-list";
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

        model.addAttribute("courseDetail", courseDetailList);
        model.addAttribute("courseTime", courseTimeList);

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
        String id = request.getSession().getId();
        return "flone/course-register";
    }

    /**
     * 새로운 강의를 등록합니다
     * @param courseRegisterRequest 강의 등록 정보
     * @param model 모델 객체
     * @return 강의 리스트로 redirect
     */
    @PostMapping("regist.hm")
    public String postCourseRegister(@ModelAttribute CourseRegisterRequest courseRegisterRequest, Model model) {
        courseService.insertCourse(courseRegisterRequest,courseTime);
        List<CourseListResponse> courseList = courseService.courseListService();
        model.addAttribute("courses", courseList);
        return "redirect:list.hm";
    }

}
