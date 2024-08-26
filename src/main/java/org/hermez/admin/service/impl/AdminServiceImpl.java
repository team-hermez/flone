package org.hermez.admin.service.impl;

import com.google.gson.Gson;
import org.hermez.admin.dto.*;
import org.hermez.admin.mapper.AdminMapper;
import org.hermez.admin.service.AdminService;
import org.hermez.common.page.Page;
import org.hermez.common.page.PaginationUtil;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.service.InstructorService;
import org.hermez.member.model.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    private final InstructorService instructorService;

    public AdminServiceImpl(AdminMapper adminMapper, InstructorService instructorService) {
        this.adminMapper = adminMapper;
        this.instructorService = instructorService;
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
    public Page<InstructorManageListResponse> getInstructorManageList(int page) {
        int total = adminMapper.selectTotalRequestRegisterCount();
        PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 10, page);
        List<InstructorManageListResponse> instructorManageListResponse = adminMapper.getInstructorManageList(pageInfo.getOffset(), pageInfo.getItemsPerPage());
        return new Page<>(instructorManageListResponse, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
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

    @Override
    public Page<InstructorListResponse> getInstructorList(int page) {
        instructorService.selectInstructorList(page);
        return instructorService.selectInstructorList(page);
    }

    @Transactional
    @Override
    public boolean approveInstructor(int instructorId) {
        int updatedStatus = adminMapper.updateInstructorStatus(instructorId);
        if (updatedStatus > 0) {
            adminMapper.updateMemberRole(instructorId);
            return true;
        }
        return false;
    }
}
