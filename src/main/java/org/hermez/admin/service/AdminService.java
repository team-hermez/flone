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

/**
 * 관리자 관련 서비스 인터페이스입니다.
 *
 * <p>이 인터페이스는 관리자 대시보드 데이터 조회, 통계 데이터 제공, 회원 관리, 강사 관리, 과정 관리, 예약 관리 등을 위한 메서드를 정의합니다.</p>
 *
 * @author 김혁진
 */
public interface AdminService {

    /**
     * 관리 대시보드의 주요 정보를 가져옵니다.
     *
     * @return {@link AdminMainResponse} 관리자 대시보드 데이터 응답 객체
     */
    AdminMainResponse getAdminMain();

    /**
     * 월별 가입 통계 데이터를 JSON 문자열로 가져옵니다.
     *
     * @return 월별 가입 통계 데이터를 포함한 JSON 문자열
     */
    String getMonthlySignupStatistics();

    /**
     * 월별 결제 통계 데이터를 JSON 문자열로 가져옵니다.
     *
     * @return 월별 결제 통계 데이터를 포함한 JSON 문자열
     */
    String getMonthlyPaymentStatistics();

    /**
     * 과목별 과정 수 통계 데이터를 JSON 문자열로 가져옵니다.
     *
     * @return 과목별 과정 수 통계 데이터를 포함한 JSON 문자열
     */
    String getCourseCountBySubjectStatistics();

    /**
     * 월별 교실 생성 통계 데이터를 JSON 문자열로 가져옵니다.
     *
     * @return 월별 교실 생성 통계 데이터를 포함한 JSON 문자열
     */
    String getMonthlyClassroomCreationStatistics();

    /**
     * 월별 과정 수 통계 데이터를 JSON 문자열로 가져옵니다.
     *
     * @return 월별 과정 수 통계 데이터를 포함한 JSON 문자열
     */
    String getMonthlyCourseCountStatistics();

    /**
     * 페이지 번호에 따라 회원 목록을 가져옵니다.
     *
     * @param page 페이지 번호
     * @return {@link Page<Member>} 회원 목록 페이지 객체
     */
    Page<Member> getMemberList(int page);

    /**
     * 페이지 번호에 따라 강사 관리 목록을 가져옵니다.
     *
     * @param page 페이지 번호
     * @return {@link Page<InstructorManageListResponse>} 강사 관리 목록 페이지 객체
     */
    Page<InstructorManageListResponse> getInstructorManageList(int page);

    /**
     * 과정 관리 목록을 가져옵니다. 과정의 유형에 따라 목록을 필터링합니다.
     *
     * @param type 과정의 유형 (예: 예약, 진행 중, 완료 등)
     * @param page 페이지 번호
     * @return {@link Page<CourseManageListResponse>} 과정 관리 목록 페이지 객체
     */
    Page<CourseManageListResponse> getCourseManageList(int type, int page);

    /**
     * 페이지 번호에 따라 강사 목록을 가져옵니다.
     *
     * @param page 페이지 번호
     * @return {@link Page<InstructorListResponse>} 강사 목록 페이지 객체
     */
    Page<InstructorListResponse> getInstructorList(int page);

    /**
     * 특정 강사를 승인합니다.
     *
     * @param instructorId 승인할 강사의 ID
     * @return 승인 성공 여부
     */
    boolean approveInstructor(int instructorId);

    /**
     * 특정 과정의 상세 정보를 가져옵니다.
     *
     * @param courseId 조회할 과정의 ID
     * @return {@link CourseDetailResponse} 과정 상세 정보 응답 객체
     */
    CourseDetailResponse getCourseDetail(int courseId);

    /**
     * 페이지 번호에 따라 모든 예약 목록을 가져옵니다.
     *
     * @param page 페이지 번호
     * @return {@link Page<ReservationListResponse>} 예약 목록 페이지 객체
     */
    Page<ReservationListResponse> getReservationListAll(int page);

    /**
     * 페이지 번호에 따라 환불 목록을 가져옵니다.
     *
     * @param page 페이지 번호
     * @return {@link Page<ReservationListResponse>} 환불 목록 페이지 객체
     */
    Page<ReservationListResponse> getRefundListAll(int page);

    /**
     * 특정 회원의 상세 정보를 가져옵니다.
     *
     * @param memberId 조회할 회원의 ID
     * @return {@link MyAccountResponse} 회원 상세 정보 응답 객체
     */
    MyAccountResponse getMemberDetail(int memberId);

    /**
     * 특정 과정의 예약 목록을 페이지 번호에 따라 가져옵니다.
     *
     * @param courseId 조회할 과정의 ID
     * @param page 페이지 번호
     * @return {@link Page<ReservationListResponse>} 예약 목록 페이지 객체
     */
    Page<ReservationListResponse> getReservationListAllByCourseId(int courseId, int page);
}
