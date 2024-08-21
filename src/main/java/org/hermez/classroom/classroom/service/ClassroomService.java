package org.hermez.classroom.classroom.service;

import org.hermez.classroom.classroom.dto.ClassroomRegisterRequest;

import java.util.Map;

/**
 * 강의룸 서비스 인터페이스입니다.
 *
 * @author 김혁진
 */
public interface ClassroomService {

    /**
     * 특정 강의에 대한 강의룸 목록을 조회하고 페이징 처리를 포함한 결과를 반환합니다.
     *
     * @param courseId 강의의 고유 ID
     * @param page 조회할 페이지 번호
     * @return 강의룸 목록과 페이징 정보를 담고 있는 Map 객체
     */
    Map<String, Object> getClassroomList(int courseId, int page);

    /**
     * 새로운 강의룸을 등록합니다.
     *
     * @param classroomRegisterRequest 등록할 강의룸의 정보를 담고 있는 요청 객체
     */
    void registerClassroom(ClassroomRegisterRequest classroomRegisterRequest);

    /**
     * 지정된 강의룸을 삭제합니다.
     *
     * @param classroomId 삭제할 강의룸의 고유 ID
     */
    void deleteClassroom(int classroomId);
}
