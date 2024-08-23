package org.hermez.course.mapper;

import org.apache.ibatis.annotations.*;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.dto.CourseListResponse;
import org.hermez.course.dto.CourseRegisterRequest;
import org.hermez.course.model.CourseTime;

import java.util.List;

/**
 * Course와 관련된 Mapper Interface입니다.
 *
 * @author 엄진수
 */

@Mapper
public interface CourseMapper {
    /**
     * 전체 강의 목록을 가져오는 Mapper입니다.
     *
     * @return 강의 전체 목록
     * List 형식으로 반환합니다
     */
    @Select("select " +
            "c.course_id as courseId," +
            "start_date as startDate, " +
            "c.end_date as endDate, " +
            "title, description, " +
            "m.name as instructorName, " +
            "img.save_name as courseImage "+
            "from course c left join instructor i on (i.instructor_id = c.instructor_id) \n"+
            "left join member m on (i.member_id = m.member_id) \n" +
            "left join image img on (img.entity_id = c.course_id) \n"+
            "order by c.start_date desc " +
            "limit #{offset}, #{itemsPerPage}")
    List<CourseListResponse> courseAllList(@Param("offset") int offset, @Param("itemsPerPage") int itemsPerPage);

    @Select("select COUNT(*) from course")
    int courseCount();

    /**
     * 강의 상세 정보를 가져오는 Mapper입니다.
     *
     * @param courseId
     * @return 강의 상세 정보
     */
    @Select("select c.course_id as courseId, " +
            "title,m.name as instructorName, " +
            "course_Price as coursePrice, " +
            "description, start_date as startDate, " +
            "end_date as endDate, " +
            "i.instructor_id as instructorId, " +
            "s.subject_name as subject, " +
            "g.grade_name as grade, " +
            "img.save_name as courseImage " +
            "from course c left join instructor i on (c.instructor_id=i.instructor_id)\n" +
            "left join member m on (i.member_id=m.member_id)\n" +
            "left join subject s on (i.subject_id = s.subject_id)\n" +
            "left join grade g on (c.grade_id = g.grade_id)\n" +
            "left join image img on (img.entity_id = c.course_id)\n" +
            "where c.course_id = #{courseId}")
    CourseDetailResponse courseDetailResponse(int courseId);

    /**
     * 강의 상세 정보의 시간 관련된 정보를 가져오는 Mapper입니다.
     * @param courseId
     * @return 강의 시간 정보
     * List 형식으로 반환합니다.
     */
    @Select("select course_id as courseId, day_of_week as dayOfWeek, start_time as startTime, end_time as endTime from course_schedule where course_id = #{courseId}")
    List<CourseTime> courseDetailTime(int courseId);

    /**
     * 신규 강의를 등록하는 Mapper입니다.
     *
     * @param courseRegisterRequest
     */
    @Insert("insert into course(instructor_id, grade_id, title, created_at, updated_at, description, start_date, end_date, course_price)" +
            " values(1,#{gradeId},#{title},now(),now(),#{description},#{startDate},#{endDate},#{coursePrice})")
    @Options(useGeneratedKeys = true, keyProperty = "courseId")
    void insertCourse(CourseRegisterRequest courseRegisterRequest);

    /**
     * 신규 강의의 시간 정보를 등록하는 Mapper입니다
     *
     * @param courseTime
     */
    @Insert("insert into course_schedule (course_id, day_of_week, start_time, end_time) " +
            "values (#{courseId},#{dayOfWeek}, #{startTime},#{endTime})")
    void insertCourseSchedule(CourseTime courseTime);

}
