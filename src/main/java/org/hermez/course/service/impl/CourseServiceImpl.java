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
import org.hermez.image.dto.RegisterImageRequest;
import org.hermez.image.exception.ImageProcessingException;
import org.hermez.image.mapper.ImageMapper;
import org.hermez.image.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final ImageService imageService;

    /**
     * {@inheritDoc}
     */
    public CourseServiceImpl(CourseMapper courseMapper, ImageService imageService) {
        this.courseMapper = courseMapper;
        this.imageService = imageService;
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
        RegisterImageRequest imageRequest = new RegisterImageRequest();
        imageRequest.setEntityId(courseRegisterRequest.getCourseId());
        imageRequest.setEntityType("COURSE");
        imageRequest.setMultipartFile(courseRegisterRequest.getImageFile());
        try {
            imageService.saveImage(imageRequest);
        } catch (IOException e) {
            throw new ImageProcessingException("이미지 저장에 실패했습니다.", e);
        } catch (Exception e) {
            throw new RuntimeException("강의 등록 중 알 수 없는 오류가 발생했습니다.", e);
        }
        int courseId = courseRegisterRequest.getCourseId();
        List<CourseTime> courseTimes = courseRegisterRequest.getCourseTimes();
        for (CourseTime item : courseTimes) {
            item.setCourseId(courseId);
            courseMapper.insertCourseSchedule(item);
        }
    }
}
