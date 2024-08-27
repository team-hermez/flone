package org.hermez.course.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.hermez.common.page.Page;
import org.hermez.common.page.PaginationUtil;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.dto.CourseListResponse;
import org.hermez.course.dto.CourseRegisterRequest;
import org.hermez.course.exception.CourseRegisterTimeException;
import org.hermez.course.mapper.CourseMapper;
import org.hermez.course.model.CourseTime;
import org.hermez.course.service.CourseService;
import org.hermez.image.dto.RegisterImageRequest;
import org.hermez.image.exception.ImageProcessingException;
import org.hermez.image.mapper.ImageMapper;
import org.hermez.image.service.ImageService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
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
    @Override
    public int getAllCourseCount(){
        return courseMapper.courseCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CourseListResponse> getCourseListByCategory(String category, String subject, String instructorName, String grade, int page){
        if(category.equals("instructorName"))
            System.out.println("교사명");

        if(category.equals("subject")) {
            int total = courseMapper.getCourseCountBySubject(subject);
            PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 9, page);
            List<CourseListResponse> courses = courseMapper.getCourseListBySubject(subject, pageInfo.getOffset(), pageInfo.getItemsPerPage());
            return new Page<>(courses, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
        }else if(category.equals("instructorName")) {
            int total = courseMapper.getCourseCountByName(instructorName);
            PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 9, page);
            List<CourseListResponse> courses = courseMapper.getCourseListByName(instructorName, pageInfo.getOffset(), pageInfo.getItemsPerPage());
            return new Page<>(courses, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
        }else {
            int total = courseMapper.getCourseCountByGrade(grade);
            PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 9, page);
            List<CourseListResponse> courses = courseMapper.getCourseListByGrade(grade, pageInfo.getOffset(), pageInfo.getItemsPerPage());
            return new Page<>(courses, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
        }
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

    @Override
    public List<CourseListResponse> getCourseListByInstructor (String instructorName){
        return courseMapper.courseListByInstructor(instructorName);
    }

    @Override
    public int getInstructorIdByMemberId(int memberId){
        return courseMapper.getInstructorIdByMemberId(memberId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertCourse(CourseRegisterRequest courseRegisterRequest) throws CourseRegisterTimeException {
        boolean flag = checkDay(courseRegisterRequest);
        if(flag) {
            courseMapper.insertCourse(courseRegisterRequest);
            int courseId = courseRegisterRequest.getCourseId();

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

            List<CourseTime> courseTimes = courseRegisterRequest.getCourseTimes();
            for (CourseTime item : courseTimes) {
                item.setCourseId(courseId);
                courseMapper.insertCourseSchedule(item);
            }
        }else
            throw new CourseRegisterTimeException("강의 등록 실패했습니다");
    }

    public boolean checkDay(CourseRegisterRequest courseRegisterRequest){
        List<CourseTime> courseTimesList =courseRegisterRequest.getCourseTimes();
        String firstDay = courseTimesList.get(0).getDayOfWeek();

        for (int i = 1; i < courseTimesList.size(); i++) {
            if(courseTimesList.get(i).getDayOfWeek().equals(firstDay)){
                boolean checkStartTime = checkStartTime(courseRegisterRequest);
                boolean checkEndTime = checkEndTime(courseRegisterRequest);
                if (checkStartTime && checkEndTime) {
                    return true;
                }else
                    throw new CourseRegisterTimeException("동일한 날짜에 겹치는 시간 강의 예약은 불가능합니다.");
                }
            }
        return true;
    }
    public boolean checkStartTime(CourseRegisterRequest courseRegisterRequest){
        List<CourseTime> courseTimesList = courseRegisterRequest.getCourseTimes();
        String firstTime = courseTimesList.get(0).getStartTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for(int i = 0; i < courseTimesList.size(); i++) {
            try {
                String startTime = courseTimesList.get(i).getStartTime();
                LocalTime firstStartTime = LocalTime.parse(firstTime, formatter);
                LocalTime anotherStartTime = LocalTime.parse(startTime, formatter);
                if(firstStartTime.equals(anotherStartTime)){
                    throw new CourseRegisterTimeException("동일한 시작 시간이 있습니다.");
                }
            } catch (DateTimeParseException dateTimeParseException) {
                throw new CourseRegisterTimeException("올바른 시간형식이 아닙니다.");
            }
        }
        return true;
    }
    public boolean checkEndTime(CourseRegisterRequest courseRegisterRequest){
        List<CourseTime> courseTimesList =courseRegisterRequest.getCourseTimes();
        String firstTime = courseTimesList.get(0).getEndTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for(int i = 0; i < courseTimesList.size(); i++) {
            try{
                String endTime = courseTimesList.get(i).getEndTime();
                LocalTime firstEndTime = LocalTime.parse(firstTime, formatter);
                LocalTime anotherEndTime = LocalTime.parse(endTime, formatter);
                if(firstEndTime.equals(anotherEndTime)){
                    throw new CourseRegisterTimeException("동일한 종료 시간이 있습니다.");
                }
            }catch (DateTimeParseException dateTimeParseException) {
                throw new RuntimeException("올바른 시간형식이 아닙니다.");
            }
        }
        return true;
    }
}
