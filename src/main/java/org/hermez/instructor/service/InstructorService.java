package org.hermez.instructor.service;
import org.hermez.common.page.Page;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.dto.InstructorRegisterRequest;

import java.util.ArrayList;

public interface InstructorService {
    Page<InstructorListResponse> selectInstructorList(int page);

    void insertInstructor(InstructorRegisterRequest instructorRegisterRequest);
}
