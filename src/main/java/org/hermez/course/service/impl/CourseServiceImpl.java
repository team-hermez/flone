package org.hermez.course.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hermez.common.page.Page;
import org.hermez.common.page.PaginationUtil;
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
 * {@inheritDoc}
 *
 * @author 엄진수
 */
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;

    /**
     * {@inheritDoc}
     */
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CourseListResponse> getCourseList(int page) {
        int total = courseMapper.courseCount();
        PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 9,page);
        List<CourseListResponse> courses = courseMapper.courseAllList(pageInfo.getOffset(), pageInfo.getItemsPerPage());
        return new Page<>(courses, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CourseDetailResponse courseDetailService(int courseId) {
        return courseMapper.courseDetailResponse(courseId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CourseTime> courseDetailTime(int courseId) {
        return courseMapper.courseDetailTime(courseId);
    }

    /**
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
