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

@Controller
@RequestMapping("flone/admin/course")
public class AdminCourseController {

    private final AdminService adminService;

    public AdminCourseController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("manage-course.hm")
    public String getManageCoursePage(Model model, @RequestParam("type") int type, @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<CourseManageListResponse> courses = adminService.getCourseManageList(type, page);
        model.addAttribute("type", type);
        model.addAttribute("courses", courses);

        return "flone/admin-course";
    }

    @GetMapping("manage-course-detail.hm")
    public String getManageCourseDetailPage(Model model, @RequestParam("courseId") int courseId, @RequestParam(value = "page", defaultValue = "1") int page) {
        CourseDetailResponse courseDetail = adminService.getCourseDetail(courseId);
        Page<ReservationListResponse> reservationListResponse = adminService.getReservationListAllByCourseId(courseId, page);
        model.addAttribute("courseDetail", courseDetail);
        model.addAttribute("reservations", reservationListResponse);

        return "flone/admin-course-detail";
    }
}
