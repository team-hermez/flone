package org.hermez.admin.controller;

import org.hermez.admin.service.AdminService;
import org.hermez.common.page.Page;
import org.hermez.member.dto.MemberLoginResponse;
import org.hermez.member.model.Member;
import org.hermez.reservation.dto.MyReservedReservationDTO;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.model.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("flone/admin/member")
public class AdminMemberController {

    private final AdminService adminService;
    private final ReservationRepository reservationRepository;

    public AdminMemberController(AdminService adminService, ReservationRepository reservationRepository) {
        this.adminService = adminService;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("manage-member.hm")
    public String getManageMemberPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Page<Member> memberPage = adminService.getMemberList(page);

        model.addAttribute("memberPage", memberPage);
        model.addAttribute("monthlySignupChartData", adminService.getMonthlySignupStatistics());

        return "flone/admin-member";
    }

    @GetMapping("member-detail.hm")
    public String getMemberDetailPage(@RequestParam int memberId,@RequestParam(value = "page", defaultValue = "1") int page, Model model){
        Page<ReservationListResponse> reservationPage = reservationRepository.getReservationList(memberId,page);
        model.addAttribute("member", adminService.getMemberDetail(memberId));
        model.addAttribute("memberId", memberId);
        model.addAttribute("reservationPage", reservationPage);
        return "flone/admin-member-detail";
    }


}
