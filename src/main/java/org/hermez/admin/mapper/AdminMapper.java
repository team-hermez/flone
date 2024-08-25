package org.hermez.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hermez.admin.dto.*;
import org.hermez.member.model.Member;

import java.util.List;
import java.util.Map;

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

    @Select("SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, COUNT(*) AS count " +
            "FROM course " +
            "WHERE created_at >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH) " +
            "GROUP BY DATE_FORMAT(created_at, '%Y-%m') " +
            "ORDER BY month")
    List<MonthlySignupResponse> getMonthlyCourseCount();

    @Select("SELECT " +
            "member_id AS memberId, " +
            "role_id AS roleId, " +
            "email, " +
            "name, " +
            "password, " +
            "phone, " +
            "DATE_FORMAT(created_at, '%Y-%m-%d') AS createdAt " +
            "FROM member")
    List<Member> getAllMembers();


    @Select("SELECT m.email, m.name, m.phone, m.created_at AS createdAt, s.subject_name AS subjectName " +
            "FROM instructor i " +
            "JOIN member m ON i.member_id = m.member_id " +
            "JOIN subject s ON i.subject_id = s.subject_id " +
            "WHERE i.instructor_status = 1")
    List<InstructorManageListResponse> getInstructorManageList();

    @Select("SELECT " +
            "c.course_id AS courseId, " +
            "g.grade_name AS gradeName, " +
            "s.subject_name AS subjectName, " +
            "m.name AS instructorName, " +
            "c.created_at AS createdAt, " +
            "c.course_price AS coursePrice, " +
            "COUNT(r.member_id) AS studentCount, " +
            "(c.course_price * COUNT(r.member_id)) AS totalRevenue, " +
            "c.start_date AS startDate, " +
            "c.end_date AS endDate " +
            "FROM course c " +
            "JOIN instructor i ON c.instructor_id = i.instructor_id " +
            "JOIN member m ON i.member_id = m.member_id " +
            "JOIN subject s ON i.subject_id = s.subject_id " +
            "JOIN grade g ON c.grade_id = g.grade_id " +
            "LEFT JOIN reservation r ON c.course_id = r.course_id " +
            "WHERE CURDATE() <= c.start_date " +
            "GROUP BY c.course_id")
    List<CourseManageListResponse> getReservationCourses();


    @Select("SELECT " +
            "c.course_id AS courseId, " +
            "g.grade_name AS gradeName, " +
            "s.subject_name AS subjectName, " +
            "m.name AS instructorName, " +
            "c.created_at AS createdAt, " +
            "c.course_price AS coursePrice, " +
            "COUNT(r.member_id) AS studentCount, " +
            "(c.course_price * COUNT(r.member_id)) AS totalRevenue, " +
            "c.start_date AS startDate, " +
            "c.end_date AS endDate " +
            "FROM course c " +
            "JOIN instructor i ON c.instructor_id = i.instructor_id " +
            "JOIN member m ON i.member_id = m.member_id " +
            "JOIN subject s ON i.subject_id = s.subject_id " +
            "JOIN grade g ON c.grade_id = g.grade_id " +
            "LEFT JOIN reservation r ON c.course_id = r.course_id " +
            "WHERE CURDATE() BETWEEN c.start_date AND c.end_date " +
            "GROUP BY c.course_id")
    List<CourseManageListResponse> getProgressCourses();

    @Select("SELECT " +
            "c.course_id AS courseId, " +
            "g.grade_name AS gradeName, " +
            "s.subject_name AS subjectName, " +
            "m.name AS instructorName, " +
            "c.created_at AS createdAt, " +
            "c.course_price AS coursePrice, " +
            "COUNT(r.member_id) AS studentCount, " +
            "(c.course_price * COUNT(r.member_id)) AS totalRevenue, " +
            "c.start_date AS startDate, " +
            "c.end_date AS endDate " +
            "FROM course c " +
            "JOIN instructor i ON c.instructor_id = i.instructor_id " +
            "JOIN member m ON i.member_id = m.member_id " +
            "JOIN subject s ON i.subject_id = s.subject_id " +
            "JOIN grade g ON c.grade_id = g.grade_id " +
            "LEFT JOIN reservation r ON c.course_id = r.course_id " +
            "WHERE CURDATE() > c.end_date " +
            "GROUP BY c.course_id")
    List<CourseManageListResponse> getFinishedCourses();

    @Select("SELECT COUNT(*) FROM member WHERE DATE(created_at) = CURDATE()")
    int getDailySignUpCount();

    @Select("SELECT COUNT(*) FROM member WHERE YEAR(created_at) = YEAR(CURDATE()) AND MONTH(created_at) = MONTH(CURDATE())")
    int getMonthlySignUpCount();

    @Select("SELECT COUNT(*) FROM member")
    int getTotalSignUpCount();

}
