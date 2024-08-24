package org.hermez.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hermez.admin.dto.CourseReservationRankResponse;
import org.hermez.admin.dto.MonthlyPaymentHistoryResponse;
import org.hermez.admin.dto.MonthlySignupResponse;
import org.hermez.admin.dto.SubjectCourseCountResponse;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, COUNT(*) AS count " +
            "FROM member " +
            "GROUP BY DATE_FORMAT(created_at, '%Y-%m') " +
            "ORDER BY month")
    List<MonthlySignupResponse> getMonthlySignupStatistics();

    @Select("SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, " +
            "SUM(CASE WHEN cancel_at IS NULL THEN payment_amount ELSE 0 END) AS revenue, " +
            "SUM(CASE WHEN cancel_at IS NOT NULL THEN payment_amount ELSE 0 END) AS refund " +
            "FROM payment_history " +
            "WHERE created_at >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH) " +
            "GROUP BY DATE_FORMAT(created_at, '%Y-%m') " +
            "ORDER BY month")
    List<MonthlyPaymentHistoryResponse> getMonthlyRevenueAndRefund();

    @Select("SELECT s.subject_name AS subjectName, COUNT(c.course_id) AS courseCount " +
            "FROM subject s " +
            "LEFT JOIN instructor i ON s.subject_id = i.subject_id " +
            "LEFT JOIN course c ON i.instructor_id = c.instructor_id " +
            "GROUP BY s.subject_name")
    List<SubjectCourseCountResponse> getCourseCountBySubject();

    @Select("SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, COUNT(*) AS count " +
            "FROM classroom " +
            "GROUP BY DATE_FORMAT(created_at, '%Y-%m') " +
            "ORDER BY month")
    List<MonthlySignupResponse> getMonthlyClassroomCreationStatistics();

    @Select("SELECT c.title AS courseTitle, COUNT(r.reservation_id) AS reservationCount " +
            "FROM course c " +
            "JOIN reservation r ON c.course_id = r.course_id " +
            "GROUP BY c.course_id, c.title " +
            "ORDER BY reservationCount DESC " +
            "LIMIT 5")
    List<CourseReservationRankResponse> getTop5CoursesByReservations();
}
