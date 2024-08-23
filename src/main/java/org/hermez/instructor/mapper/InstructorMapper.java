package org.hermez.instructor.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hermez.instructor.dto.InstructorDetailResponse;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.dto.InstructorRegisterRequest;

import java.util.ArrayList;

@Mapper
public interface InstructorMapper {
    @Select("select m.name, instructor_id as instructorId, i.member_id as memberID, subject_id as subjectID, instructor_description as instructorDescription, instructor_status as instructorStatus " +
            "from instructor i, member m where m.member_id = i.member_id and i.instructor_status = 1 limit #{offset}, #{itemsPerPage}")
    ArrayList<InstructorListResponse> selectInstructorList(@Param("offset") int offset, @Param("itemsPerPage") int itemPerPage);

    @Select("select COUNT(*) from instructor where instructor_status = 1")
    int instructorCount();

    @Insert("insert into INSTRUCTOR (subject_id, member_id, instructor_title, instructor_description, instructor_status) " +
            "values (#{subjectId}, #{memberId},#{instructorTitle}, #{instructorDescription}, #{instructorStatus})")
    void insertInstructor(InstructorRegisterRequest instructorRegisterRequest);

    @Select("select * from instructor where instructor_id = #{instructorsID}")
    ArrayList<InstructorDetailResponse> selectInstructorDetail(int instructorsID);

}
