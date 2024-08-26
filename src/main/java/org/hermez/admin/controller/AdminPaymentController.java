package org.hermez.admin.controller;

import org.hermez.admin.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("flone/admin")
public class AdminPaymentController {

    private final AdminService adminService;

    public AdminPaymentController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("payment/manage-payment.hm")
    public String getManagePaymentPage(Model model) {
        String chartDataJson = adminService.getMonthlyPaymentStatistics();
        model.addAttribute("chartDataJson", chartDataJson);

        return "flone/admin-payment";
    }

    @GetMapping("payment/manage-refund.hm")
    public String getManageRefundPage(Model model) {
        String chartDataJson = adminService.getMonthlyPaymentStatistics();
        model.addAttribute("chartDataJson", chartDataJson);

        return "flone/admin-refund";
    }
}
