package org.hermez.admin.service;

import org.hermez.admin.dto.AdminMainResponse;
import org.hermez.admin.dto.CourseManageListResponse;
import org.hermez.admin.dto.InstructorManageListResponse;
import org.hermez.common.page.Page;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.member.dto.MyAccountResponse;
import org.hermez.member.model.Member;
import org.hermez.reservation.dto.ReservationListResponse;

public interface AdminService {

    AdminMainResponse getAdminMain();

    String getMonthlySignupStatistics();

    String getMonthlyPaymentStatistics();

    String getCourseCountBySubjectStatistics();

    String getMonthlyClassroomCreationStatistics();

    String getMonthlyCourseCountStatistics();

    Page<Member> getMemberList(int page);

    Page<InstructorManageListResponse> getInstructorManageList(int page);

    Page<CourseManageListResponse> getCourseManageList(int type,int page);

    Page<InstructorListResponse> getInstructorList(int page);

    boolean approveInstructor(int instructorId);

    CourseDetailResponse getCourseDetail(int courseId);

    Page<ReservationListResponse> getReservationListAll(int page);

    Page<ReservationListResponse> getRefundListAll(int page);

    MyAccountResponse getMemberDetail(int memberId);
}
