package org.hermez.admin.service;

import org.hermez.admin.dto.AdminMainResponse;
import org.hermez.admin.dto.CourseManageListResponse;
import org.hermez.admin.dto.InstructorManageListResponse;
import org.hermez.common.page.Page;
import org.hermez.member.model.Member;

import java.util.List;

public interface AdminService {

    AdminMainResponse getAdminMain();

    String getMonthlySignupStatistics();

    String getMonthlyPaymentStatistics();

    String getCourseCountBySubjectStatistics();

    String getMonthlyClassroomCreationStatistics();

    String getMonthlyCourseCountStatistics();

    Page<Member> getMemberList(int page);

    List<InstructorManageListResponse> getInstructorManageList();

    List<CourseManageListResponse> getCourseManageList(int type);
}
