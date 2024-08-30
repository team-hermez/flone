package org.hermez.admin.controller;

import org.hermez.admin.service.AdminService;
import org.hermez.common.page.Page;
import org.hermez.member.model.Member;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.model.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 관리자 페이지의 회원 관리 및 상세 정보 관리를 담당하는 컨트롤러입니다.
 *
 * <p>이 클래스는 관리자 페이지에서 회원 목록을 조회하고, 개별 회원의 상세 정보를 제공하는 기능을 포함합니다.</p>
 *
 * @author 김혁진
 */
@Controller
@RequestMapping("flone/admin/member")
public class AdminMemberController {

    private final AdminService adminService;
    private final ReservationRepository reservationRepository;

    /**
     * 생성자입니다.
     *
     * @param adminService 관리자 서비스 객체
     * @param reservationRepository 예약 리포지토리 객체
     */
    public AdminMemberController(AdminService adminService, ReservationRepository reservationRepository) {
        this.adminService = adminService;
        this.reservationRepository = reservationRepository;
    }

    /**
     * 회원 관리 페이지를 반환합니다.
     *
     * @param page 페이지 번호 (기본값은 1)
     * @param model 모델 객체
     * @return 회원 관리 페이지의 뷰 이름
     */
    @GetMapping("manage-member.hm")
    public String getManageMemberPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Page<Member> memberPage = adminService.getMemberList(page);

        model.addAttribute("memberPage", memberPage);
        model.addAttribute("monthlySignupChartData", adminService.getMonthlySignupStatistics());

        return "flone/admin-member";
    }

    /**
     * 회원 상세 정보를 반환합니다.
     *
     * @param memberId 회원 ID
     * @param page 페이지 번호 (기본값은 1)
     * @param model 모델 객체
     * @return 회원 상세 페이지의 뷰 이름
     */
    @GetMapping("member-detail.hm")
    public String getMemberDetailPage(@RequestParam int memberId, @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Page<ReservationListResponse> reservationPage = reservationRepository.getReservationList(memberId, page);
        model.addAttribute("member", adminService.getMemberDetail(memberId));
        model.addAttribute("memberId", memberId);
        model.addAttribute("reservationPage", reservationPage);
        return "flone/admin-member-detail";
    }

}
