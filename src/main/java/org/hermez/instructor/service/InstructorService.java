package org.hermez.instructor.service;
import org.hermez.common.page.Page;
import org.hermez.instructor.dto.InstructorDetailResponse;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.dto.InstructorRegisterRequest;
import org.hermez.instructor.model.Instructor;
import org.hermez.member.dto.MemberLoginResponse;

import java.util.ArrayList;

public interface InstructorService {

    Page<InstructorListResponse> selectInstructorList(int page);

    InstructorDetailResponse selectInstructorDetail(int instructorsID);

    int instructorsCount();

    void insertInstructor(InstructorRegisterRequest instructorRegisterRequest);

    Instructor findByMemberLoginResponseId(MemberLoginResponse memberLoginResponse);
}
