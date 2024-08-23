package org.hermez.instructor.service;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.dto.InstructorRegisterRequest;

import java.util.ArrayList;

public interface InstructorService {
    ArrayList<InstructorListResponse> selectInstructorList();

    void insertInstructor(InstructorRegisterRequest instructorRegisterRequest);
}
