package org.hermez.admin.controller;

import org.hermez.admin.dto.InstructorManageListResponse;
import org.hermez.admin.service.AdminService;
import org.hermez.common.page.Page;
import org.hermez.instructor.dto.InstructorListResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("flone/admin/instructor")
public class AdminInstructorController {

    private final AdminService adminService;

    public AdminInstructorController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("manage-instructor.hm")
    public String getManageInstructorPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Page<InstructorListResponse> instructors = adminService.getInstructorList(page);
        model.addAttribute("instructors", instructors);

        return "flone/admin-instructor-list";
    }

    @GetMapping("request-register.hm")
    public String getRegisterInstructorListPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Page<InstructorManageListResponse> instructors = adminService.getInstructorManageList(page);
        model.addAttribute("instructors", instructors);

        return "flone/admin-instructor-register";
    }

    @PostMapping("register-instructor.hm")
    public String postRegisterInstructorListPage(int instructorId){
        adminService.approveInstructor(instructorId);
        return "redirect:request-register.hm";
    }
}
