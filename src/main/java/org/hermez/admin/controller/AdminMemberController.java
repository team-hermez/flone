package org.hermez.admin.controller;

import org.hermez.admin.service.AdminService;
import org.hermez.common.page.Page;
import org.hermez.member.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("flone/admin")
public class AdminMemberController {

    private final AdminService adminService;

    public AdminMemberController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("manage-member.hm")
    public String getManageMemberPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Page<Member> memberPage = adminService.getMemberList(page);

        model.addAttribute("memberPage", memberPage);
        model.addAttribute("monthlySignupChartData", adminService.getMonthlySignupStatistics());

        return "flone/admin-member";
    }

    @GetMapping("memeber-detail.hm")
    public String getMemberDetailPage(@RequestParam int memberId, Model model){
        model.addAttribute("member", adminService.getMemberDetail(memberId));
        return "flone/admin-member-detail";
    }
}
