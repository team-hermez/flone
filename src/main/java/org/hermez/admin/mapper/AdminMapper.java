package org.hermez.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hermez.admin.dto.*;
import org.hermez.member.model.Member;

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

    @Select("SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, COUNT(*) AS count " +
            "FROM course " +
            "WHERE created_at >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH) " +
            "GROUP BY DATE_FORMAT(created_at, '%Y-%m') " +
            "ORDER BY month")
    List<MonthlySignupResponse> getMonthlyCourseCount();

    @Select("SELECT " +
            "member_id AS memberId, role_id AS roleId, " +
            "email, name, password, phone," +
            "DATE_FORMAT(created_at, '%Y-%m-%d') AS createdAt " +
            "FROM member " +
            "ORDER BY created_at DESC " +
            "LIMIT #{offset}, #{itemsPerPage}")
    List<Member> selectMemberList(@Param("offset") int offset, @Param("itemsPerPage") int itemPerPage);

    @Select("SELECT i.instructor_id as instructorId, m.email, m.name, m.phone, m.created_at AS createdAt, s.subject_name AS subjectName " +
            "FROM instructor i " +
            "JOIN member m ON i.member_id = m.member_id " +
            "JOIN subject s ON i.subject_id = s.subject_id " +
            "WHERE i.instructor_status = 0 " +
            "ORDER BY created_at DESC " +
            "LIMIT #{offset}, #{itemsPerPage}")
            List<InstructorManageListResponse> getInstructorManageList(@Param("offset") int offset, @Param("itemsPerPage") int itemPerPage);

    @Select("SELECT " +
            "c.course_id AS courseId, " +
            "c.title AS courseTitle, " +
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
            "GROUP BY c.course_id " +
            "LIMIT #{offset}, #{itemsPerPage}")
    List<CourseManageListResponse> getReservationCourses(@Param("offset") int offset, @Param("itemsPerPage") int itemPerPage);


    @Select("SELECT " +
            "c.course_id AS courseId, " +
            "c.title AS courseTitle, " +
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
            "GROUP BY c.course_id " +
            "LIMIT #{offset}, #{itemsPerPage}")
    List<CourseManageListResponse> getProgressCourses(@Param("offset") int offset, @Param("itemsPerPage") int itemPerPage);

    @Select("SELECT " +
            "c.course_id AS courseId, " +
            "c.title AS courseTitle, " +
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
            "GROUP BY c.course_id " +
            "LIMIT #{offset}, #{itemsPerPage}")
    List<CourseManageListResponse> getFinishedCourses(@Param("offset") int offset, @Param("itemsPerPage") int itemPerPage);

    @Select("SELECT COUNT(*) FROM member WHERE DATE(created_at) = CURDATE()")
    int getDailySignUpCount();

    @Select("SELECT COUNT(*) FROM member WHERE YEAR(created_at) = YEAR(CURDATE()) AND MONTH(created_at) = MONTH(CURDATE())")
    int getMonthlySignUpCount();

    @Select("SELECT COUNT(*) FROM member")
    int getTotalSignUpCount();

    @Select("SELECT COUNT(*) FROM instructor where instructor_status = 0")
    int selectTotalRequestRegisterCount();

    @Update("UPDATE instructor SET instructor_status = 1 WHERE instructor_id = #{instructorId} AND instructor_status = 0")
    int updateInstructorStatus(@Param("instructorId") int instructorId);

    @Update("UPDATE member SET role_id = 2 WHERE member_id = (SELECT member_id FROM instructor WHERE instructor_id = #{instructorId})")
    int updateMemberRole(@Param("instructorId") int instructorId);

    @Select("SELECT COUNT(*) FROM course where CURDATE() <= start_date")
    int selectTotalCountReservationCourse();

    @Select("SELECT COUNT(*) FROM course WHERE CURDATE() BETWEEN start_date AND end_date")
    int selectTotalCountProgressCourse();

    @Select("SELECT COUNT(*) FROM course WHERE CURDATE() > end_date")
    int selectTotalCountFinishedCourse();
}
