package org.hermez.admin.service;

import org.hermez.member.model.Member;

import java.util.List;

public interface AdminService {

    String getMonthlySignupStatistics();

    String getMonthlyPaymentStatistics();

    String getCourseCountBySubject();

    String getMonthlyClassroomCreationStatistics();

    String getTop5CoursesByReservations();

    List<Member> getAllMembers();
}
