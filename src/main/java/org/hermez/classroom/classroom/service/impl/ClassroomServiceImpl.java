package org.hermez.classroom.classroom.service.impl;

import org.hermez.classroom.classroom.dto.ClassroomCardResponse;
import org.hermez.classroom.classroom.dto.ClassroomRegisterRequest;
import org.hermez.classroom.classroom.exception.ClassroomNotFoundException;
import org.hermez.classroom.classroom.mapper.ClassroomMapper;
import org.hermez.classroom.classroom.service.ClassroomService;
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
    public Map<String, Object> getClassroomList(int courseId, int page) {
        int offset = (page - 1) * 9;
        int total = classroomMapper.countClassrooms(courseId);
        int totalPages = total % 9 == 0 ? total / 9 : total / 9 + 1;
        List<ClassroomCardResponse> classrooms = classroomMapper.selectClassroomList(courseId, offset);

        if (classrooms.isEmpty()) {
            throw new ClassroomNotFoundException("해당 코스에 강의실이 존재하지 않습니다.");
        }

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
}
