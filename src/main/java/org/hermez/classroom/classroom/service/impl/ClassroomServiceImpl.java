package org.hermez.classroom.classroom.service.impl;

import org.hermez.classroom.classroom.dto.ClassroomCardResponse;
import org.hermez.classroom.classroom.dto.ClassroomRegisterRequest;
import org.hermez.classroom.classroom.exception.ClassroomNotFoundException;
import org.hermez.classroom.classroom.mapper.ClassroomMapper;
import org.hermez.classroom.classroom.service.ClassroomService;
import org.hermez.common.page.Page;
import org.hermez.common.page.PaginationUtil;
import org.hermez.image.dto.RegisterImageRequest;
import org.hermez.image.exception.ImageProcessingException;
import org.hermez.image.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 *
 * @author 김혁진
 */
@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomMapper classroomMapper;
    private final ImageService imageService;

    /**
     * {@inheritDoc}
     */
    public ClassroomServiceImpl(ClassroomMapper classroomMapper, ImageService imageService) {
        this.classroomMapper = classroomMapper;
        this.imageService = imageService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ClassroomCardResponse> getClassroomList(int courseId, int page) {
        int total = classroomMapper.countClassrooms(courseId);
        PaginationUtil.PageInfo pageInfo = PaginationUtil.calculatePagination(total, 9, page);
        List<ClassroomCardResponse> classrooms = classroomMapper.selectClassroomList(courseId, pageInfo.getOffset(), pageInfo.getItemsPerPage());
        return new Page<>(classrooms, pageInfo.getTotalItems(), pageInfo.getTotalPages(), pageInfo.getCurrentPage());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void registerClassroom(ClassroomRegisterRequest classroomRegisterRequest) {
        classroomMapper.insertClassroom(classroomRegisterRequest);
        int classroomId = classroomRegisterRequest.getClassroomId();
        RegisterImageRequest registerImageRequest = new RegisterImageRequest();
        registerImageRequest.setEntityId(classroomId);
        registerImageRequest.setMultipartFile(classroomRegisterRequest.getImageFile());
        registerImageRequest.setEntityType("classroom");

        try {
            imageService.saveImage(registerImageRequest);
        } catch (IOException e) {
            throw new ImageProcessingException("이미지 저장에 실패했습니다.", e);
        } catch (Exception e) {
            throw new RuntimeException("강의실 등록 중 알 수 없는 오류가 발생했습니다.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteClassroom(int classroomId) {
        int deletedClassroom = classroomMapper.deleteClassroomById(classroomId);
        if (deletedClassroom == 0) {
            throw new ClassroomNotFoundException("삭제할 강의실이 존재하지 않습니다. ID: " + classroomId);
        }
    }

    public int getTotalClassroomCount(){
        return classroomMapper.selectCountAll();
    }
}
