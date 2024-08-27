package org.hermez.admin.controller;

import org.hermez.admin.service.AdminService;
import org.hermez.common.page.Page;
import org.hermez.reservation.dto.ReservationListResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("flone/admin/payment")
public class AdminPaymentController {

    private final AdminService adminService;

    public AdminPaymentController(AdminService adminService) {
        this.adminService = adminService;
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
}
