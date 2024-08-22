package org.hermez.course.service;

import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.dto.CourseListResponse;
import org.hermez.course.model.CourseTime;

import java.util.List;

/**
 *  강의 관련 Interface입니다.
 *  
 * @author 엄진수
 */
public interface CourseService {
    /**
     * 전체 강의 목록을 조회합니다.
     * @return 전체 강의 목록 List
     */
    List<CourseListResponse> courseListService();

    /**
     * 상세 페이지를 조회합니다
     *
     * @param courseId
     * @return courseId와 일치하는 Data
     */
    CourseDetailResponse courseDetailService(int courseId);

    /**
     * courseId와 일치하는 course의 시간 정보를 조회합니다.
     * @param courseId
     * @return course의 시간표 List
     */
    List<CourseTime> courseDetailTime(int courseId);
}
