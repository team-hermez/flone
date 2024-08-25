package org.hermez.instructor.service.impl;

import lombok.RequiredArgsConstructor;
import org.hermez.common.page.Page;
import org.hermez.common.page.PaginationUtil;
import org.hermez.image.service.ImageService;
import org.hermez.instructor.dto.InstructorDetailResponse;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.dto.InstructorRegisterRequest;
import org.hermez.instructor.mapper.InstructorMapper;

import org.hermez.instructor.model.Instructor;
import org.hermez.instructor.service.InstructorService;
import org.hermez.member.dto.MemberLoginResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorMapper instructorMapper;
    private final ImageService imageService;

    @Override
    public Page<InstructorListResponse> selectInstructorList(int page) {
        int total = instructorMapper.instructorCount();
        PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 4, page);
        List<InstructorListResponse> instructors = instructorMapper.selectInstructorList(pageInfo.getOffset(), pageInfo.getItemsPerPage());
        return new Page<>(instructors, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
    }

    @Override
    public ArrayList<InstructorDetailResponse> selectInstructorDetail(int instructorsID) {
        ArrayList<InstructorDetailResponse> instructorDetails = instructorMapper.selectInstructorDetail(instructorsID);
        return instructorDetails;
    }


    @Override
    public void insertInstructor(InstructorRegisterRequest instructorRegisterRequest) {
        instructorMapper.insertInstructor(instructorRegisterRequest);
    }

    @Override
    public Instructor findByMemberLoginResponseId(MemberLoginResponse memberLoginResponse) {
        return instructorMapper.findByMemberId(memberLoginResponse);
    }
}
