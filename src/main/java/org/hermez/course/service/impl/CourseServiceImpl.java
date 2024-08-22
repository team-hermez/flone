package org.hermez.course.service.impl;

import org.hermez.course.dto.CourseListResponse;
import org.hermez.course.mapper.CourseMapper;
import org.hermez.course.service.CourseService;
import org.springframework.stereotype.Service;

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
}
