package org.hermez.course.controller;

import org.hermez.course.dto.CourseListResponse;
import org.hermez.course.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "flone/list";
    }
}
