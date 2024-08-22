package org.hermez.course.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hermez.course.dto.CourseDetailResponse;
import org.hermez.course.dto.CourseListResponse;
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
     * 전체 강의 목록을 가져오는 SQL입니다.
     *
     * @return List<CourseListResponse> courseAllList();
     * List 형식으로 반환합니다
     */
    @Select("select c.course_id as courseId,start_date as startDate, c.end_date as endDate, title, description, m.name as instructorName\n" +
            "from course c left join instructor i on (c.instructor_id = i.instructor_id)\n" +
            "left join member m on (i.member_id = m.member_id)\n" +
            "order by start_date desc")
    List<CourseListResponse> courseAllList();

    @Select("select title,m.name as instructorName,course_Price as coursePrice, description, start_date as startDate, end_date as endDate, i.instructor_id as instructorId, s.subject_name as subject, grade_name as grade\n" +
            "from course c left join instructor i on (c.instructor_id=i.instructor_id)\n" +
            "left join member m on (i.member_id=m.member_id)\n" +
            "left join subject s on (i.subject_id = s.subject_id)\n" +
            "left join grade g on (c.grade_id = g.grade_id)\n" +
            "where c.course_id = #{courseId}")
    CourseDetailResponse courseDetailResponse(int courseId);

    @Select("select course_id as courseId, day_of_week as dayOfWeek, start_time as startTime, end_time as endTime from course_schedule where course_id = #{courseId}")
    List<CourseTime> courseDetailTime(int courseId);
}
