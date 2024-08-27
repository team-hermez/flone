package org.hermez.instructor.controller;

import org.hermez.common.page.Page;
import org.hermez.instructor.dto.InstructorDetailResponse;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.dto.InstructorRegisterRequest;
import org.hermez.instructor.service.InstructorService;
import org.hermez.member.dto.MemberLoginResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("flone/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("list.hm")
    public String getInstructorList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {
        Page<InstructorListResponse> instructorList = instructorService.selectInstructorList(page);
        model.addAttribute("instructors", instructorList);
        return "flone/instructor-list";
    }

    @GetMapping("register.hm")
    public String getInstructorRegisterPage() {
        return "flone/instructor-register";
    }

    @GetMapping("detail.hm")
    public String getInstructorDetailPage(@RequestParam int instructorId, Model model) {
        InstructorDetailResponse instructorDetailResponses = instructorService.selectInstructorDetail(instructorId);
        model.addAttribute("instructorDetail", instructorDetailResponses);
        return "flone/instructor-detail";
    }

    @PostMapping("regist.hm")
    public String postInstructorRegister(InstructorRegisterRequest instructorRegisterRequest, HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        MemberLoginResponse member = (MemberLoginResponse) session.getAttribute("MEMBER");
        instructorRegisterRequest.setMemberId(member.getMemberId());
        instructorService.insertInstructor(instructorRegisterRequest);
        return "redirect:/flone/instructor/list.hm";
    }
}
