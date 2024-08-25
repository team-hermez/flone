package org.hermez.admin.service.impl;

import com.google.gson.Gson;
import org.hermez.admin.dto.*;
import org.hermez.admin.mapper.AdminMapper;
import org.hermez.admin.service.AdminService;
import org.hermez.common.page.Page;
import org.hermez.common.page.PaginationUtil;
import org.hermez.member.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminMainResponse getAdminMain() {
        AdminMainResponse adminMainResponse = new AdminMainResponse();
        adminMainResponse.setDailySignUpCount(adminMapper.getDailySignUpCount());
        adminMainResponse.setMonthlySignUpCount(adminMapper.getMonthlySignUpCount());
        adminMainResponse.setTotalSignUpCount(adminMapper.getTotalSignUpCount());
        return adminMainResponse;
    }

    @Override
    public String getMonthlySignupStatistics() {
        List<MonthlySignupResponse> monthlySignupResponses = adminMapper.getMonthlySignupStatistics();
        return new Gson().toJson(monthlySignupResponses);
    }

    @Override
    public String getMonthlyPaymentStatistics() {
        List<MonthlyPaymentHistoryResponse> monthlyPaymentHistoryResponses = adminMapper.getMonthlyRevenueAndRefund();
        return new Gson().toJson(monthlyPaymentHistoryResponses);
    }

    @Override
    public String getCourseCountBySubjectStatistics() {
        List<SubjectCourseCountResponse> subjectCourseCountResponse = adminMapper.getCourseCountBySubject();
        return new Gson().toJson(subjectCourseCountResponse);
    }

    @Override
    public String getMonthlyClassroomCreationStatistics() {
        List<MonthlySignupResponse> statistics = adminMapper.getMonthlyClassroomCreationStatistics();
        return new Gson().toJson(statistics);
    }

    @Override
    public String getMonthlyCourseCountStatistics() {
        List<MonthlySignupResponse> monthlyCourseCount = adminMapper.getMonthlyCourseCount();
        return new Gson().toJson(monthlyCourseCount);
    }

    @Override
    public Page<Member> getMemberList(int page) {
        int total = adminMapper.getTotalSignUpCount();
        PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 10, page);
        List<Member> members = adminMapper.selectMemberList(pageInfo.getOffset(), pageInfo.getItemsPerPage());
        return new Page<>(members, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
    }

    @Override
    public List<InstructorManageListResponse> getInstructorManageList() {
        return adminMapper.getInstructorManageList();
    }

    @Override
    public List<CourseManageListResponse> getCourseManageList(int type) {
        if (type == 1) {
            return adminMapper.getReservationCourses();
        }
        if (type == 2) {
            return adminMapper.getProgressCourses();
        }
        return adminMapper.getFinishedCourses();
    }
}
