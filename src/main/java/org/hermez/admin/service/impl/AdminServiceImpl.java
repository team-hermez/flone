package org.hermez.admin.service.impl;

import com.google.gson.Gson;
import org.hermez.admin.dto.*;
import org.hermez.admin.mapper.AdminMapper;
import org.hermez.admin.service.AdminService;
import org.hermez.classroom.classroom.service.ClassroomService;
import org.hermez.common.page.Page;
import org.hermez.common.page.PaginationUtil;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.service.CourseService;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.service.InstructorService;
import org.hermez.member.dto.MyAccountResponse;
import org.hermez.member.model.Member;
import org.hermez.member.service.MemberService;
import org.hermez.reservation.dto.ReservationListResponse;
import org.hermez.reservation.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link AdminService}의 구현체로, 관리자가 사용할 다양한 기능을 제공합니다.
 *
 * <p>이 서비스는 관리자 대시보드 데이터 조회, 통계 데이터 제공, 회원 관리, 강사 관리, 과정 관리 및 예약 관리를 처리합니다.</p>
 *
 * @author 김혁진
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    private final InstructorService instructorService;
    private final CourseService courseService;
    private final ReservationService reservationService;
    private final ClassroomService classroomService;
    private final MemberService memberService;

    /**
     * {@link AdminServiceImpl}의 생성자입니다.
     *
     * @param adminMapper 관리자 관련 데이터베이스 작업을 처리하는 매퍼 객체
     * @param instructorService 강사 관련 서비스 객체
     * @param courseService 과정 관련 서비스 객체
     * @param reservationService 예약 관련 서비스 객체
     * @param classroomService 교실 관련 서비스 객체
     * @param memberService 회원 관련 서비스 객체
     */
    public AdminServiceImpl(AdminMapper adminMapper, InstructorService instructorService, CourseService courseService, ReservationService reservationService, ClassroomService classroomService, MemberService memberService) {
        this.adminMapper = adminMapper;
        this.instructorService = instructorService;
        this.courseService = courseService;
        this.reservationService = reservationService;
        this.classroomService = classroomService;
        this.memberService = memberService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdminMainResponse getAdminMain() {
        AdminMainResponse adminMainResponse = new AdminMainResponse();
        adminMainResponse.setDailySignUpCount(adminMapper.getDailySignUpCount());
        adminMainResponse.setMonthlySignUpCount(adminMapper.getMonthlySignUpCount());
        adminMainResponse.setTotalSignUpCount(adminMapper.getTotalSignUpCount());
        adminMainResponse.setTotalClassroomCount(classroomService.getTotalClassroomCount());
        adminMainResponse.setTotalInstructorCount(instructorService.instructorsCount());
        adminMainResponse.setTotalCourseCount(courseService.getAllCourseCount());

        return adminMainResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMonthlySignupStatistics() {
        List<MonthlySignupResponse> monthlySignupResponses = adminMapper.getMonthlySignupStatistics();
        return new Gson().toJson(monthlySignupResponses);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMonthlyPaymentStatistics() {
        List<MonthlyPaymentHistoryResponse> monthlyPaymentHistoryResponses = adminMapper.getMonthlyRevenueAndRefund();
        return new Gson().toJson(monthlyPaymentHistoryResponses);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCourseCountBySubjectStatistics() {
        List<SubjectCourseCountResponse> subjectCourseCountResponse = adminMapper.getCourseCountBySubject();
        return new Gson().toJson(subjectCourseCountResponse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMonthlyClassroomCreationStatistics() {
        List<MonthlySignupResponse> statistics = adminMapper.getMonthlyClassroomCreationStatistics();
        return new Gson().toJson(statistics);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMonthlyCourseCountStatistics() {
        List<MonthlySignupResponse> monthlyCourseCount = adminMapper.getMonthlyCourseCount();
        return new Gson().toJson(monthlyCourseCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Member> getMemberList(int page) {
        int total = adminMapper.getTotalSignUpCount();
        PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 10, page);
        List<Member> members = adminMapper.selectMemberList(pageInfo.getOffset(), pageInfo.getItemsPerPage());
        return new Page<>(members, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<InstructorManageListResponse> getInstructorManageList(int page) {
        int total = adminMapper.selectTotalRequestRegisterCount();
        PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 10, page);
        List<InstructorManageListResponse> instructorManageListResponse = adminMapper.getInstructorManageList(pageInfo.getOffset(), pageInfo.getItemsPerPage());
        return new Page<>(instructorManageListResponse, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CourseManageListResponse> getCourseManageList(int type, int page) {
        if (type == 1) {
            int total = adminMapper.selectTotalCountReservationCourse();
            PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 10, page);
            List<CourseManageListResponse> courseManageListResponse = adminMapper.getReservationCourses(pageInfo.getOffset(), pageInfo.getItemsPerPage());
            return new Page<>(courseManageListResponse, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
        }
        if (type == 2) {
            int total = adminMapper.selectTotalCountProgressCourse();
            PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 10, page);
            List<CourseManageListResponse> courseManageListResponse = adminMapper.getProgressCourses(pageInfo.getOffset(), pageInfo.getItemsPerPage());
            return new Page<>(courseManageListResponse, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
        }
        int total = adminMapper.selectTotalCountFinishedCourse();
        PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 10, page);
        List<CourseManageListResponse> courseManageListResponse = adminMapper.getFinishedCourses(pageInfo.getOffset(), pageInfo.getItemsPerPage());
        return new Page<>(courseManageListResponse, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<InstructorListResponse> getInstructorList(int page) {
        return instructorService.selectInstructorList(page);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean approveInstructor(int instructorId) {
        int updatedStatus = adminMapper.updateInstructorStatus(instructorId);
        if (updatedStatus > 0) {
            adminMapper.updateMemberRole(instructorId);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CourseDetailResponse getCourseDetail(int courseId) {
        return courseService.courseDetailService(courseId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ReservationListResponse> getReservationListAll(int page){
        return reservationService.getReservationListAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ReservationListResponse> getRefundListAll(int page){
        return reservationService.getRefundListAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MyAccountResponse getMemberDetail(int memberId){
        return memberService.getMyAccount(memberId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ReservationListResponse> getReservationListAllByCourseId(int courseId, int page) {
        return reservationService.getReservationListAllByCourseId(courseId, page);
    }
}
