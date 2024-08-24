package org.hermez.admin.controller;

import org.hermez.admin.service.AdminService;
import org.hermez.member.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("flone/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("main.hm")
    public String getAdminMainPage(Model model) {
        String monthlySignupData = adminService.getMonthlySignupStatistics();
        String chartDataJson = adminService.getMonthlyPaymentStatistics();
        String courseCountJson = adminService.getCourseCountBySubject();
        String classroomCreationData = adminService.getMonthlyClassroomCreationStatistics();
        String topCoursesData = adminService.getTop5CoursesByReservations();

        model.addAttribute("statisticsJson", monthlySignupData);
        model.addAttribute("chartDataJson", chartDataJson);
        model.addAttribute("courseCountJson", courseCountJson);
        model.addAttribute("classroomCreationData", classroomCreationData);
        model.addAttribute("topCoursesData", topCoursesData);

        return "flone/admin-main";
    }

    @GetMapping("manage-member.hm")
    public String getManageMemberPage(Model model) {
        String monthlySignupData = adminService.getMonthlySignupStatistics();
        model.addAttribute("statisticsJson", monthlySignupData);
        List<Member> members = adminService.getAllMembers();
        model.addAttribute("members", members);

        return "flone/admin-member";
    }

    @GetMapping("manage-instructor.hm")
    public String getManageInstructorPage(Model model) {
        String monthlySignupData = adminService.getMonthlySignupStatistics();
        model.addAttribute("statisticsJson", monthlySignupData);
        List<Member> members = adminService.getAllMembers();
        model.addAttribute("members", members);

        return "flone/admin-member";
    }

}
