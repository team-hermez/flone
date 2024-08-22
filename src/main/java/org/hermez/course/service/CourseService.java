package org.hermez.course.service;

import org.hermez.course.dto.CourseListResponse;

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
}
