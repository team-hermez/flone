package org.hermez.instructor.service.impl;

import lombok.RequiredArgsConstructor;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.dto.InstructorRegisterRequest;
import org.hermez.instructor.mapper.InstructorMapper;

import org.hermez.instructor.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorMapper instructorMapper;

    @Override
    public ArrayList<InstructorListResponse> selectInstructorList() {
        ArrayList<InstructorListResponse> instructors = instructorMapper.selectInstructorList();
        return instructors;
    }

    @Override
    public void insertInstructor(InstructorRegisterRequest instructorRegisterRequest) {
        instructorMapper.insertInstructor(instructorRegisterRequest);
    }
}
