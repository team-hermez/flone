package org.hermez.admin.controller;

import org.hermez.admin.service.AdminService;
import org.hermez.common.page.Page;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.model.CourseTime;
import org.hermez.course.service.CourseService;
import org.hermez.reservation.dto.MyPaymentDetailResponse;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.model.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 관리자 페이지의 결제 및 환불 관리를 담당하는 컨트롤러입니다.
 *
 * <p>이 클래스는 관리자 페이지에서 결제 및 환불 정보를 조회하고 세부 사항을 확인하는 기능을 제공합니다.</p>
 *
 * @author 김혁진
 */
@Controller
@RequestMapping("flone/admin/payment")
public class AdminPaymentController {

    private final AdminService adminService;
    private final CourseService courseService;
    private final ReservationRepository reservationRepository;

    /**
     * 생성자입니다.
     *
     * @param adminService 관리자 서비스 객체
     * @param courseService 강좌 서비스 객체
     * @param reservationRepository 예약 리포지토리 객체
     */
    public AdminPaymentController(AdminService adminService, CourseService courseService, ReservationRepository reservationRepository) {
        this.adminService = adminService;
        this.courseService = courseService;
        this.reservationRepository = reservationRepository;
    }

    /**
     * 결제 관리 페이지를 반환합니다.
     *
     * @param model 모델 객체
     * @param page 페이지 번호 (기본값은 1)
     * @return 결제 관리 페이지의 뷰 이름
     */
    @GetMapping("manage-payment.hm")
    public String getManagePaymentPage(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        String chartDataJson = adminService.getMonthlyPaymentStatistics();
        Page<ReservationListResponse> reservations = adminService.getReservationListAll(page);

        model.addAttribute("chartDataJson", chartDataJson);
        model.addAttribute("reservations", reservations);

        return "flone/admin-payment";
    }

    /**
     * 환불 관리 페이지를 반환합니다.
     *
     * @param model 모델 객체
     * @param page 페이지 번호 (기본값은 1)
     * @return 환불 관리 페이지의 뷰 이름
     */
    @GetMapping("manage-refund.hm")
    public String getManageRefundPage(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        String chartDataJson = adminService.getMonthlyPaymentStatistics();
        Page<ReservationListResponse> refunds = adminService.getRefundListAll(page);

        model.addAttribute("chartDataJson", chartDataJson);
        model.addAttribute("refunds", refunds);

        return "flone/admin-refund";
    }

    /**
     * 결제 상세 페이지를 반환합니다.
     *
     * @param model 모델 객체
     * @param courseId 강좌 ID
     * @param merchantUid 결제 ID
     * @return 결제 상세 페이지의 뷰 이름
     */
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
