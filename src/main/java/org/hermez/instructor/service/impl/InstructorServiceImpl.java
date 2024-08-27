package org.hermez.instructor.service.impl;

import lombok.RequiredArgsConstructor;
import org.hermez.common.page.Page;
import org.hermez.common.page.PaginationUtil;
import org.hermez.image.dto.RegisterImageRequest;
import org.hermez.image.exception.ImageProcessingException;
import org.hermez.image.service.ImageService;
import org.hermez.instructor.dto.InstructorDetailResponse;
import org.hermez.instructor.dto.InstructorListResponse;
import org.hermez.instructor.dto.InstructorRegisterRequest;
import org.hermez.instructor.mapper.InstructorMapper;

import org.hermez.instructor.model.Instructor;
import org.hermez.instructor.service.InstructorService;
import org.hermez.member.dto.MemberLoginResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
        PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 8, page);
        List<InstructorListResponse> instructors = instructorMapper.selectInstructorList(pageInfo.getOffset(), pageInfo.getItemsPerPage());
        return new Page<>(instructors, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
    }

    @Override
    public InstructorDetailResponse selectInstructorDetail(int instructorsID) {
        InstructorDetailResponse instructorDetails = instructorMapper.selectInstructorDetail(instructorsID);
        instructorDetails.setSaveImage(imageService.getSaveImage(instructorsID,"INSTRUCTOR"));
        return instructorDetails;
    }

    @Override
    public int instructorsCount() {
        int total = instructorMapper.instructorCount();
        return total;
    }


    @Transactional
    @Override
    public void insertInstructor(InstructorRegisterRequest instructorRegisterRequest) {
        instructorMapper.insertInstructor(instructorRegisterRequest);
        int instructorId = instructorRegisterRequest.getInstructorId();
        RegisterImageRequest registerImageRequest = new RegisterImageRequest();
        registerImageRequest.setEntityId(instructorId);
        registerImageRequest.setMultipartFile(instructorRegisterRequest.getSaveImage());
        registerImageRequest.setEntityType("INSTRUCTOR");
        try {
            imageService.saveImage(registerImageRequest);
        } catch (IOException e) {
            throw new ImageProcessingException("이미지 저장에 실패했습니다.", e);
        } catch (Exception e) {
            throw new RuntimeException("신청서 작성 중 알 수 없는 오류가 발생했습니다.", e);
        }
    }

    @Override
    public Instructor findByMemberLoginResponseId(MemberLoginResponse memberLoginResponse) {
        return instructorMapper.findByMemberId(memberLoginResponse);
    }
}
