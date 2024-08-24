package org.hermez.admin.controller;

import org.hermez.admin.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
