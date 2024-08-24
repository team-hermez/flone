package org.hermez.admin.service.impl;

import com.google.gson.Gson;
import org.hermez.admin.dto.CourseReservationRankResponse;
import org.hermez.admin.dto.MonthlyPaymentHistoryResponse;
import org.hermez.admin.dto.MonthlySignupResponse;
import org.hermez.admin.dto.SubjectCourseCountResponse;
import org.hermez.admin.mapper.AdminMapper;
import org.hermez.admin.service.AdminService;
import org.hermez.member.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public String getMonthlySignupStatistics() {
        List<MonthlySignupResponse> monthlySignupResponses = adminMapper.getMonthlySignupStatistics();
        return new Gson().toJson(monthlySignupResponses);
    }

    public String getMonthlyPaymentStatistics() {
        List<MonthlyPaymentHistoryResponse> monthlyPaymentHistoryResponses = adminMapper.getMonthlyRevenueAndRefund();
        return new Gson().toJson(monthlyPaymentHistoryResponses);
    }

    public String getCourseCountBySubject() {
        List<SubjectCourseCountResponse> subjectCourseCountResponse = adminMapper.getCourseCountBySubject();
        return new Gson().toJson(subjectCourseCountResponse);
    }

    public String getMonthlyClassroomCreationStatistics() {
        List<MonthlySignupResponse> statistics = adminMapper.getMonthlyClassroomCreationStatistics();
        return new Gson().toJson(statistics);
    }

    public String getTop5CoursesByReservations() {
        List<CourseReservationRankResponse> topCourses = adminMapper.getTop5CoursesByReservations();
        return new Gson().toJson(topCourses);
    }

    public List<Member> getAllMembers() {
        return adminMapper.getAllMembers();
    }

}
