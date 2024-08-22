package org.hermez.instructor.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hermez.instructor.dto.InstructorListResponse;

import java.util.ArrayList;

@Mapper
public interface InstructorMapper {
    @Select("select instructor_id as instructorId, member_id as memberID, subject_id as subjectID, instructor_description as instructorDescription, instructor_status as instructorStatus from instructor")
    ArrayList<InstructorListResponse> selectInstructorList();
}
