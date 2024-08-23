package org.hermez.course.service;

import org.hermez.common.page.Page;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.dto.CourseListResponse;
import org.hermez.course.dto.CourseRegisterRequest;
import org.hermez.course.model.CourseTime;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *  강의 관련 Interface입니다.
 *  
 * @author 엄진수
 */
public interface CourseService {
    /**
     * 전체 강의 목록을 조회합니다.
     * @param page 조회할 페이지 번호
     * @return 강의 목록과 페이지 정보를 담고 있는 Map 객체
     */
    Page<CourseListResponse> getCourseList(int page);

    /**
     * 상세 페이지를 조회합니다
     * @param courseId course의 pk값
     * @return courseId와 일치하는 Data
     */
    CourseDetailResponse courseDetailService(int courseId);

    /**
     * courseId와 일치하는 course의 시간 정보를 조회합니다.
     * @param courseId course의 pk값
     * @return course의 시간표 List
     */
    List<CourseTime> courseDetailTime(int courseId);

    /**
     * 강의를 등록합니다
     * @param courseRegisterRequest 강의의 상세 정보
     * @param courseTime 강의 시간
     */
    void insertCourse(CourseRegisterRequest courseRegisterRequest);
}
