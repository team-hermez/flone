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

/**
 * 강사 관련 관리 페이지를 담당하는 컨트롤러입니다.
 *
 * <p>이 클래스는 강사의 목록을 관리하고, 강사의 등록 요청을 처리하며, 강사의 등록 승인을 수행합니다.</p>
 *
 * @author 김혁진
 */
@Controller
@RequestMapping("flone/admin/instructor")
public class AdminInstructorController {

    private final AdminService adminService;

    /**
     * 생성자입니다.
     *
     * @param adminService 관리자 서비스 객체
     */
    public AdminInstructorController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 강사 목록 페이지를 반환합니다.
     *
     * <p>이 메서드는 현재 등록된 강사들의 목록을 조회하여 모델에 추가합니다.
     * 이를 통해 관리자는 강사 목록을 볼 수 있습니다.</p>
     *
     * @param page 페이지 번호 (기본값: 1)
     * @param model 모델 객체, 뷰에 전달할 데이터를 설정하는 데 사용됩니다.
     * @return 강사 목록 페이지의 뷰 이름
     */
    @GetMapping("manage-instructor.hm")
    public String getManageInstructorPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Page<InstructorListResponse> instructors = adminService.getInstructorList(page);
        model.addAttribute("instructors", instructors);

        return "flone/admin-instructor-list";
    }

    /**
     * 강사 등록 요청 목록 페이지를 반환합니다.
     *
     * <p>이 메서드는 강사 등록 요청 목록을 조회하여 모델에 추가합니다.
     * 이를 통해 관리자는 새로 등록을 요청한 강사들을 볼 수 있습니다.</p>
     *
     * @param page 페이지 번호 (기본값: 1)
     * @param model 모델 객체, 뷰에 전달할 데이터를 설정하는 데 사용됩니다.
     * @return 강사 등록 요청 목록 페이지의 뷰 이름
     */
    @GetMapping("request-register.hm")
    public String getRegisterInstructorListPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Page<InstructorManageListResponse> instructors = adminService.getInstructorManageList(page);
        model.addAttribute("instructors", instructors);

        return "flone/admin-instructor-register";
    }

    /**
     * 강사의 등록 요청을 승인합니다.
     *
     * <p>이 메서드는 특정 강사의 등록 요청을 승인하고, 요청 승인 후 강사 등록 요청 목록 페이지로 리디렉션합니다.</p>
     *
     * @param instructorId 승인할 강사의 ID
     * @return 강사 등록 요청 목록 페이지로 리디렉션
     */
    @PostMapping("register-instructor.hm")
    public String postRegisterInstructorListPage(int instructorId){
        adminService.approveInstructor(instructorId);
        return "redirect:request-register.hm";
    }
}
