package org.hermez.admin.controller;

import org.hermez.admin.service.AdminService;
import org.hermez.classroom.classroom.dto.ClassroomCardResponse;
import org.hermez.classroom.classroom.service.ClassroomService;
import org.hermez.common.page.Page;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.model.CourseTime;
import org.hermez.course.service.CourseService;
import org.hermez.reservation.dto.MyPaymentDetailResponse;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.model.ReservationRepository;
import org.hermez.reservation.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("flone/admin/payment")
public class AdminPaymentController {

    private final AdminService adminService;
    private final CourseService courseService;
    private final ReservationRepository reservationRepository;

    public AdminPaymentController(AdminService adminService, CourseService courseService, ReservationRepository reservationRepository) {
        this.adminService = adminService;
        this.courseService = courseService;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("manage-payment.hm")
    public String getManagePaymentPage(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        String chartDataJson = adminService.getMonthlyPaymentStatistics();
        Page<ReservationListResponse> reservations = adminService.getReservationListAll(page);

        model.addAttribute("chartDataJson", chartDataJson);
        model.addAttribute("reservations", reservations);

        return "flone/admin-payment";
    }

    @GetMapping("manage-refund.hm")
    public String getManageRefundPage(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        String chartDataJson = adminService.getMonthlyPaymentStatistics();
        Page<ReservationListResponse> refunds = adminService.getRefundListAll(page);

        model.addAttribute("chartDataJson", chartDataJson);
        model.addAttribute("refunds", refunds);

        return "flone/admin-refund";
    }

    @GetMapping("payment-detail.hm")
    public String getManagePaymentDetailPage(Model model, @RequestParam int courseId, @RequestParam String merchantUid) {
        MyPaymentDetailResponse myPaymentDetail = reservationRepository.findMyPaymentDetail(merchantUid);
        CourseDetailResponse courseDetailList = courseService.courseDetailService(courseId);
        List<CourseTime> courseTimeList = courseService.courseDetailTime(courseId);
        model.addAttribute("courseDetailList", courseDetailList);
        model.addAttribute("courseTimeList", courseTimeList);
        model.addAttribute("paymentDetail", myPaymentDetail);
        return "flone/admin-payment-detail";
    }

}
