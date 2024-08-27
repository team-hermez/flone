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
import java.time.LocalDateTime;
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

            System.out.println("checkException");

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
        int dayCount = courseTimesList.size();
        if(dayCount == 1) return true;
        boolean flag = false;

        for (int i = 0; i < dayCount; i++) {
            for (int j = dayCount-1; j > i; j--) {
                if ((courseTimesList.get(j).getDayOfWeek()).equals(courseTimesList.get(i).getDayOfWeek())) {
                    int checkStart = i;
                    int checkEnd = j;
                    flag = checkTime(courseRegisterRequest,checkStart,checkEnd);
                }
            }
        }
        return flag;
    }

    public boolean checkTime(CourseRegisterRequest courseRegisterRequest,int checkStart,int checkEnd){
        List<CourseTime> courseTimesList =courseRegisterRequest.getCourseTimes();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        String firstStart = courseTimesList.get(checkStart).getStartTime();
        String firstEnd = courseTimesList.get(checkStart).getEndTime();
        String anotherStart = courseTimesList.get(checkEnd).getStartTime();
        String anotherEnd = courseTimesList.get(checkEnd).getEndTime();

        System.out.println(firstStart);
        System.out.println(firstEnd);
        try{
            LocalTime firstStartTime = LocalTime.parse(firstStart, formatter);
            LocalTime firstEndTime = LocalTime.parse(firstEnd, formatter);
            LocalTime anotherStartTime = LocalTime.parse(anotherStart, formatter);
            LocalTime anotherEndTime = LocalTime.parse(anotherEnd, formatter);

            System.out.println(firstStartTime);
            System.out.println(firstEndTime);
            System.out.println(anotherStartTime);
            System.out.println(anotherEndTime);
            if(firstStartTime.isBefore(anotherEndTime)&&firstEndTime.isBefore(anotherStartTime)) {
                System.out.println(firstStartTime.isBefore(anotherEndTime));
                System.out.println(firstEndTime.isBefore(anotherStartTime));
                return true;
            }
            throw new CourseRegisterTimeException("동일한 요일에 시간대가 겹칠 수 없습니다.");
        }catch (DateTimeParseException e) {
            throw new CourseRegisterTimeException("시간 변환하는 과정에서 오류 발생했습니다.");
        }
    }
}
