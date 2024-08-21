package org.hermez.classroom.classroom.service.impl;

import org.hermez.classroom.classroom.dto.ClassroomCardResponse;
import org.hermez.classroom.classroom.dto.ClassroomRegisterRequest;
import org.hermez.classroom.classroom.mapper.ClassroomMapper;
import org.hermez.classroom.classroom.service.ClassroomService;
import org.hermez.image.dto.RegisterImageRequest;
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
 * <p>이 클래스는 {@link ClassroomService}의 구현체로, 강의룸 관련 비즈니스 로직을 처리합니다.
 * 강의룸 목록 조회, 등록, 삭제 및 이미지 저장 기능을 제공합니다.</p>
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
    public Map<String, Object> getClassroomList(int courseId, int page) {
        int offset = (page - 1) * 9;
        int total = classroomMapper.countClassrooms(courseId);
        int totalPages = total % 9 == 0 ? total / 9 : total / 9 + 1;
        List<ClassroomCardResponse> classrooms = classroomMapper.selectClassroomList(courseId, offset);

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("classrooms", classrooms);
        result.put("currentPage", page);
        result.put("totalPages", totalPages);

        return result;
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
            throw new RuntimeException("Failed to save image", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteClassroom(int classroomId) {
        classroomMapper.deleteClassroomById(classroomId);
    }
}
