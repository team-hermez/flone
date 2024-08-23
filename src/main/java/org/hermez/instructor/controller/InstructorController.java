package org.hermez.instructor.controller;

import org.hermez.common.page.Page;
import org.hermez.course.dto.CourseListResponse;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.dto.InstructorRegisterRequest;
import org.hermez.instructor.mapper.InstructorMapper;
import org.hermez.instructor.model.Instructor;
import org.hermez.instructor.service.InstructorService;
import org.hermez.member.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//TODO 강사 등록페이지, 강사 권한 관리자한테 요청

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
    public String getInstructorRegisterPage(Model model) {
        return "flone/instructor-register";
    }

    @GetMapping("detail.hm")
    public String getInstructorDetailPage(@RequestParam int instructorId) {
        return "flone/instructor-detail";
    }

    @PostMapping("regist.hm")
    public String PostInstructorRegister(@SessionAttribute("MEMBER") Member member, @RequestParam int subjectId, InstructorRegisterRequest instructorRegisterRequest, Model model) {
        instructorRegisterRequest.setMemberId(member.getMemberId());
        instructorRegisterRequest.setSubjectId(subjectId);
        instructorService.insertInstructor(instructorRegisterRequest);
        return "redirect:/flone/instructor/list.hm";
    }
}
