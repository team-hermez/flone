package org.hermez.instructor.controller;

import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.mapper.InstructorMapper;
import org.hermez.instructor.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

//TODO Controller 못 찾는거, 강사 등록페이지, 강사 상세페이지, 강사 권한 관리자한테 요청

@Controller
@RequestMapping("flone/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("list.hm")
    public String getInstructor_list(Model model) {
        ArrayList<InstructorListResponse> instructorList = instructorService.selectInstructorList();
        model.addAttribute("instructors", instructorList);
        return "flone/instructor-list";
    }
}
