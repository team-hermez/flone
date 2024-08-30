package org.hermez.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hermez.admin.dto.*;
import org.hermez.member.model.Member;

import java.util.List;

/**
 * 관리자 관련 데이터베이스 작업을 처리하는 MyBatis 매퍼 인터페이스입니다.
 *
 * <p>이 인터페이스는 관리자 대시보드에 필요한 통계 및 데이터 조회 작업을 위한 SQL 쿼리를 정의합니다.</p>
 *
 * @author 김혁진
 */
@Mapper
public interface AdminMapper {

    /**
     * 월별 회원 가입 통계를 조회합니다.
     *
     * @return 월별 가입 수를 포함하는 리스트
     */
    @Select("SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, COUNT(*) AS count " +
            "FROM member " +
            "GROUP BY DATE_FORMAT(created_at, '%Y-%m') " +
            "ORDER BY month")
    List<MonthlySignupResponse> getMonthlySignupStatistics();

    /**
     * 월별 매출 및 환불 통계를 조회합니다.
     *
     * @return 월별 매출 및 환불 내역을 포함하는 리스트
     */
    @Select("SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, " +
            "SUM(CASE WHEN cancel_at IS NULL THEN payment_amount ELSE 0 END) AS revenue, " +
            "SUM(CASE WHEN cancel_at IS NOT NULL THEN payment_amount ELSE 0 END) AS refund " +
            "FROM payment_history " +
            "WHERE created_at >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH) " +
            "GROUP BY DATE_FORMAT(created_at, '%Y-%m') " +
            "ORDER BY month")
    List<MonthlyPaymentHistoryResponse> getMonthlyRevenueAndRefund();

    /**
     * 과목별 강좌 수 통계를 조회합니다.
     *
     * @return 과목별 강좌 수를 포함하는 리스트
     */
    @Select("SELECT s.subject_name AS subjectName, COUNT(c.course_id) AS courseCount " +
            "FROM subject s " +
            "LEFT JOIN instructor i ON s.subject_id = i.subject_id " +
            "LEFT JOIN course c ON i.instructor_id = c.instructor_id " +
            "GROUP BY s.subject_name")
    List<SubjectCourseCountResponse> getCourseCountBySubject();

    /**
     * 월별 교실 생성 통계를 조회합니다.
     *
     * @return 월별 교실 생성 수를 포함하는 리스트
     */
    @Select("SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, COUNT(*) AS count " +
            "FROM classroom " +
            "GROUP BY DATE_FORMAT(created_at, '%Y-%m') " +
            "ORDER BY month")
    List<MonthlySignupResponse> getMonthlyClassroomCreationStatistics();

    /**
     * 월별 강좌 수 통계를 조회합니다.
     *
     * @return 월별 강좌 수를 포함하는 리스트
     */
    @Select("SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, COUNT(*) AS count " +
            "FROM course " +
            "WHERE created_at >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH) " +
            "GROUP BY DATE_FORMAT(created_at, '%Y-%m') " +
            "ORDER BY month")
    List<MonthlySignupResponse> getMonthlyCourseCount();

    /**
     * 회원 목록을 페이지네이션하여 조회합니다.
     *
     * @param offset 조회 시작 위치
     * @param itemPerPage 페이지당 아이템 수
     * @return 페이지네이션된 회원 목록
     */
    @Select("SELECT " +
            "member_id AS memberId, role_id AS roleId, " +
            "email, name, encoded_password AS encodedPassword, phone, " +
            "DATE_FORMAT(created_at, '%Y-%m-%d') AS createdAt " +
            "FROM member " +
            "ORDER BY created_at DESC " +
            "LIMIT #{offset}, #{itemsPerPage}")
    List<Member> selectMemberList(@Param("offset") int offset, @Param("itemsPerPage") int itemPerPage);

    /**
     * 강사 관리 목록을 페이지네이션하여 조회합니다.
     *
     * @param offset 조회 시작 위치
     * @param itemPerPage 페이지당 아이템 수
     * @return 페이지네이션된 강사 관리 목록
     */
    @Select("SELECT i.instructor_id as instructorId, m.email, m.name, m.phone, m.created_at AS createdAt, s.subject_name AS subjectName " +
            "FROM instructor i " +
            "JOIN member m ON i.member_id = m.member_id " +
            "JOIN subject s ON i.subject_id = s.subject_id " +
            "WHERE i.instructor_status = 0 " +
            "ORDER BY created_at DESC " +
            "LIMIT #{offset}, #{itemsPerPage}")
    List<InstructorManageListResponse> getInstructorManageList(@Param("offset") int offset, @Param("itemsPerPage") int itemPerPage);

    /**
     * 예약 강좌 목록을 페이지네이션하여 조회합니다.
     *
     * @param offset 조회 시작 위치
     * @param itemPerPage 페이지당 아이템 수
     * @return 페이지네이션된 예약 강좌 목록
     */
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

    /**
     * 진행 중 강좌 목록을 페이지네이션하여 조회합니다.
     *
     * @param offset 조회 시작 위치
     * @param itemPerPage 페이지당 아이템 수
     * @return 페이지네이션된 진행 중 강좌 목록
     */
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

    /**
     * 종료된 강좌 목록을 페이지네이션하여 조회합니다.
     *
     * @param offset 조회 시작 위치
     * @param itemPerPage 페이지당 아이템 수
     * @return 페이지네이션된 종료된 강좌 목록
     */
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

    /**
     * 오늘 가입된 회원 수를 조회합니다.
     *
     * @return 오늘 가입된 회원 수
     */
    @Select("SELECT COUNT(*) FROM member WHERE DATE(created_at) = CURDATE()")
    int getDailySignUpCount();

    /**
     * 이번 달 가입된 회원 수를 조회합니다.
     *
     * @return 이번 달 가입된 회원 수
     */
    @Select("SELECT COUNT(*) FROM member WHERE YEAR(created_at) = YEAR(CURDATE()) AND MONTH(created_at) = MONTH(CURDATE())")
    int getMonthlySignUpCount();

    /**
     * 전체 가입된 회원 수를 조회합니다.
     *
     * @return 전체 가입된 회원 수
     */
    @Select("SELECT COUNT(*) FROM member")
    int getTotalSignUpCount();

    /**
     * 승인 대기 중인 강사 요청 수를 조회합니다.
     *
     * @return 승인 대기 중인 강사 요청 수
     */
    @Select("SELECT COUNT(*) FROM instructor WHERE instructor_status = 0")
    int selectTotalRequestRegisterCount();

    /**
     * 강사 상태를 승인으로 업데이트합니다.
     *
     * @param instructorId 승인할 강사의 ID
     * @return 업데이트된 행 수
     */
    @Update("UPDATE instructor SET instructor_status = 1 WHERE instructor_id = #{instructorId} AND instructor_status = 0")
    int updateInstructorStatus(@Param("instructorId") int instructorId);

    /**
     * 강사에 연관된 회원의 역할을 업데이트합니다.
     *
     * @param instructorId 강사의 ID
     * @return 업데이트된 행 수
     */
    @Update("UPDATE member SET role_id = 2 WHERE member_id = (SELECT member_id FROM instructor WHERE instructor_id = #{instructorId})")
    int updateMemberRole(@Param("instructorId") int instructorId);

    /**
     * 예약된 강좌의 총 수를 조회합니다.
     *
     * @return 예약된 강좌의 총 수
     */
    @Select("SELECT COUNT(*) FROM course WHERE CURDATE() <= start_date")
    int selectTotalCountReservationCourse();

    /**
     * 진행 중인 강좌의 총 수를 조회합니다.
     *
     * @return 진행 중인 강좌의 총 수
     */
    @Select("SELECT COUNT(*) FROM course WHERE CURDATE() BETWEEN start_date AND end_date")
    int selectTotalCountProgressCourse();

    /**
     * 종료된 강좌의 총 수를 조회합니다.
     *
     * @return 종료된 강좌의 총 수
     */
    @Select("SELECT COUNT(*) FROM course WHERE CURDATE() > end_date")
    int selectTotalCountFinishedCourse();
}
