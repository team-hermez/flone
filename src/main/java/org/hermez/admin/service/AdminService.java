package org.hermez.admin.service;

import org.hermez.admin.dto.AdminMainResponse;
import org.hermez.admin.dto.CourseManageListResponse;
import org.hermez.admin.dto.InstructorManageListResponse;
import org.hermez.member.model.Member;

import java.util.List;
import java.util.Map;

public interface AdminService {

    AdminMainResponse getAdminMain();

    String getMonthlySignupStatistics();

    String getMonthlyPaymentStatistics();

    String getCourseCountBySubjectStatistics();

    String getMonthlyClassroomCreationStatistics();

    String getMonthlyCourseCountStatistics();

    List<Member> getAllMembers();

    List<InstructorManageListResponse> getInstructorManageList();

    List<CourseManageListResponse> getCourseManageList(int type);
}
