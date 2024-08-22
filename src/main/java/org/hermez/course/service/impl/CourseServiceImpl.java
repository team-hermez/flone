package org.hermez.course.service.impl;

import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.dto.CourseListResponse;
import org.hermez.course.dto.CourseRegisterRequest;
import org.hermez.course.mapper.CourseMapper;
import org.hermez.course.model.CourseTime;
import org.hermez.course.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 강의 관련 Service 구현체 class입니다.
 *
 * @author 엄진수
 */

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;

    /**
     * 서비스 구현체의 생성자입니다.
     *
     * @param courseMapper
     */
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    /**
     * 전체 강의 목록을 조회합니다.
     * @return 전체 강의 게시판 목록
     */
    @Override
    public List<CourseListResponse> courseListService() {
        return courseMapper.courseAllList();
    }

    /**
     * 강의 상세 페이지를 조회합니다.
     * @param courseId course의 pk값
     * @return 상세 페이지
     */
    @Override
    public CourseDetailResponse courseDetailService(int courseId) {
        return courseMapper.courseDetailResponse(courseId);
    }

    /**
     * 상세 페이지의 시간 정보를 조회합니다
     * @param courseId course의 pk값
     * @return 상세 페이지 시간 List
     */
    @Override
    public List<CourseTime> courseDetailTime(int courseId) {
        return courseMapper.courseDetailTime(courseId);
    }

    /**
     * 신규 강의를 등록합니다.
     * @param courseRegisterRequest 강의의 상세 정보
     * @param courseTime 강의 시간
     */
    @Transactional
    @Override
    public void insertCourse(CourseRegisterRequest courseRegisterRequest, CourseTime courseTime) {
        courseMapper.insertCourse(courseRegisterRequest);
        int courseId = courseRegisterRequest.getCourseId();
        List<CourseTime> courseTimes = courseRegisterRequest.getCourseTimes();
        for (CourseTime item : courseTimes) {
            item.setCourseId(courseId);
            courseMapper.insertCourseSchedule(item);
        }
    }
}
