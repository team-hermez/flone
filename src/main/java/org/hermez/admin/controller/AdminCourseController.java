package org.hermez.admin.controller;

import org.hermez.admin.dto.CourseManageListResponse;
import org.hermez.admin.service.AdminService;
import org.hermez.common.page.Page;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.reservation.dto.ReservationListResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 강좌 관련 관리 페이지를 담당하는 컨트롤러입니다.
 *
 * <p>이 클래스는 강좌의 목록을 관리하고, 개별 강좌의 상세 정보를 조회하며, 해당 강좌의 예약 목록을 처리합니다.</p>
 *
 * @author 김혁진
 */
@Controller
@RequestMapping("flone/admin/course")
public class AdminCourseController {

    private final AdminService adminService;

    /**
     * 생성자입니다.
     *
     * @param adminService 관리자 서비스 객체
     */
    public AdminCourseController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 강좌 목록 페이지를 반환합니다.
     *
     * <p>이 메서드는 강좌 목록을 조회하여 모델에 추가합니다.
     * 강좌의 유형(type)과 페이지 번호를 받아 해당 페이지의 강좌 목록을 반환합니다.</p>
     *
     * @param type 강좌 유형 (예: 예약, 진행 중, 완료 등)
     * @param page 페이지 번호 (기본값: 1)
     * @param model 모델 객체, 뷰에 전달할 데이터를 설정하는 데 사용됩니다.
     * @return 강좌 목록 페이지의 뷰 이름
     */
    @GetMapping("manage-course.hm")
    public String getManageCoursePage(Model model, @RequestParam("type") int type, @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<CourseManageListResponse> courses = adminService.getCourseManageList(type, page);
        model.addAttribute("type", type);
        model.addAttribute("courses", courses);

        return "flone/admin-course";
    }

    /**
     * 강좌의 상세 정보와 예약 목록 페이지를 반환합니다.
     *
     * <p>이 메서드는 특정 강좌의 상세 정보를 조회하고, 해당 강좌에 대한 예약 목록을 조회하여 모델에 추가합니다.</p>
     *
     * @param courseId 강좌 ID
     * @param page 페이지 번호 (기본값: 1)
     * @param model 모델 객체, 뷰에 전달할 데이터를 설정하는 데 사용됩니다.
     * @return 강좌 상세 정보 페이지의 뷰 이름
     */
    @GetMapping("manage-course-detail.hm")
    public String getManageCourseDetailPage(Model model, @RequestParam("courseId") int courseId, @RequestParam(value = "page", defaultValue = "1") int page) {
        CourseDetailResponse courseDetail = adminService.getCourseDetail(courseId);
        Page<ReservationListResponse> reservationListResponse = adminService.getReservationListAllByCourseId(courseId, page);
        model.addAttribute("courseDetail", courseDetail);
        model.addAttribute("reservations", reservationListResponse);

        return "flone/admin-course-detail";
    }
}
