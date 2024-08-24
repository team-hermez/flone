package org.hermez.admin.service;

public interface AdminService {

    String getMonthlySignupStatistics();

    String getMonthlyPaymentStatistics();

    String getCourseCountBySubject();

    String getMonthlyClassroomCreationStatistics();

    String getTop5CoursesByReservations();
}
