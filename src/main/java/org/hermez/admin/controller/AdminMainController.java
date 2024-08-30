package org.hermez.admin.controller;

import org.hermez.admin.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 관리자 메인 페이지를 담당하는 컨트롤러입니다.
 *
 * <p>이 클래스는 관리자 대시보드의 메인 페이지를 표시하기 위한 데이터를 준비하고 반환합니다.
 * 페이지에 필요한 다양한 통계 정보를 조회하여 모델에 추가하고, 해당 페이지의 뷰 이름을 반환합니다.</p>
 *
 * @author 김혁진
 */
@Controller
@RequestMapping("flone/admin")
public class AdminMainController {

    private final AdminService adminService;

    /**
     * 생성자입니다.
     *
     * @param adminService 관리자 서비스 객체
     */
    public AdminMainController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 관리자 메인 페이지를 반환합니다.
     *
     * <p>이 메서드는 관리자 대시보드의 메인 페이지를 위한 데이터를 조회하여 모델에 추가합니다.
     * 이를 통해 메인 페이지에서 다양한 통계와 정보를 표시할 수 있습니다.</p>
     *
     * @param model 모델 객체, 뷰에 전달할 데이터를 설정하는 데 사용됩니다.
     * @return 관리자 메인 페이지의 뷰 이름
     */
    @GetMapping("main.hm")
    public String getAdminMainPage(Model model) {
        model.addAttribute("adminMainResponse", adminService.getAdminMain());
        model.addAttribute("monthlySignupChartData", adminService.getMonthlySignupStatistics());
        model.addAttribute("monthlyPaymentChartData", adminService.getMonthlyPaymentStatistics());
        model.addAttribute("courseCountBySubjectChartData", adminService.getCourseCountBySubjectStatistics());
        model.addAttribute("classroomCreationData", adminService.getMonthlyClassroomCreationStatistics());
        model.addAttribute("monthlyCourseCountChartData", adminService.getMonthlyCourseCountStatistics());
        return "flone/admin-main";
    }
}
