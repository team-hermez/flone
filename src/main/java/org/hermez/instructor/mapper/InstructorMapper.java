package org.hermez.instructor.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hermez.instructor.dto.InstructorDetailResponse;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.dto.InstructorRegisterRequest;
import org.hermez.instructor.model.Instructor;
import org.hermez.member.dto.MemberLoginResponse;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface InstructorMapper {

    @Select("SELECT " +
            "m.name AS name, " +
            "ins.instructor_id AS instructorId, " +
            "ins.member_id AS memberId, " +
            "ins.subject_id AS subjectId, " +
            "ins.instructor_title AS instructorTitle, " +
            "ins.instructor_status AS instructorStatus, " +
            "i.save_name AS saveImage " +
            "FROM instructor ins " +
            "LEFT JOIN image i ON ins.instructor_id = i.entity_id AND i.entity_type = 'INSTRUCTOR' " +
            "LEFT JOIN member m ON m.member_id = ins.member_id " +
            "WHERE ins.instructor_status = 1 " +
            "ORDER BY ins.instructor_id DESC " +
            "LIMIT #{offset}, #{itemsPerPage}")
    List<InstructorListResponse> selectInstructorList(@Param("offset") int offset, @Param("itemsPerPage") int itemsPerPage);

    @Select("select COUNT(*) from instructor where instructor_status = 1")
    int instructorCount();

    @Insert("insert into INSTRUCTOR (subject_id, member_id, instructor_title, instructor_description, instructor_status) " +
            "values (#{subjectId}, #{memberId},#{instructorTitle}, #{instructorDescription}, 0)")
    void insertInstructor(InstructorRegisterRequest instructorRegisterRequest);

    @Select("select m.name, instructor_id as instructorId, s.subject_name as subjectName, i.member_id as memberID, s.subject_id as subjectID, instructor_title as instructorTitle, instructor_description as instructorDescription, instructor_status as instructorStatus " +
            "from instructor i, member m, subject s where m.member_id = i.member_id and s.subject_id = i.subject_id and i.instructor_id = #{instructorID}")
    InstructorDetailResponse selectInstructorDetail(int instructorsID);

    @Select("SELECT DISTINCT m.role_id as roleId, m.name as name, m.email as email, m.encoded_password as encodedPassword, m.phone as phone, m.social_login_id as socialLoginId, m.created_at as createdAt " +
            "FROM instructor i " +
            "JOIN member m ON m.member_id = i.member_id " +
            "WHERE m.member_id = #{memberId}")
    Instructor findByMemberId(MemberLoginResponse memberLoginResponse);
}
