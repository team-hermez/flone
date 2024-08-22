package org.hermez.instructor.service;
import org.hermez.instructor.dto.InstructorListResponse;
import java.util.ArrayList;

public interface InstructorService {
    ArrayList<InstructorListResponse> selectInstructorList();
}
