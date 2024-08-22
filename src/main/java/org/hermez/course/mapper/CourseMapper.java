package org.hermez.course.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hermez.course.dto.CourseListResponse;

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
}
