package org.hermez.classroom.classroom.controller;

import org.hermez.classroom.classroom.dto.ClassroomRegisterRequest;
import org.hermez.classroom.classroom.service.ClassroomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 강의룸 컨트롤러 클래스입니다.
 * 이 클래스는 강의와 관련된 여러 페이지에 대한 요청을 처리하며,
 * 강의룸 목록 조회, 등록, 삭제 기능을 제공합니다.
 *
 * @author 김혁진
 */
@Controller
@RequestMapping("flone/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    /**
     * ClassroomController의 생성자입니다.
     *
     * @param classroomService 강의룸 관련 비즈니스 로직을 처리하는 서비스
     */
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    /**
     * 강의룸 목록을 조회하고 해당 페이지를 반환하는 메서드입니다.
     *
     * @param courseId 강의의 고유 ID
     * @param page 조회할 페이지 번호
     * @param model 뷰로 데이터를 전달하기 위한 모델 객체
     * @return 강의룸 목록 페이지 경로
     */
    @GetMapping("classroom-list.hm")
    public String getClassroomList(
            @RequestParam(value = "courseId") int courseId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {

        Map<String, Object> classroomData = classroomService.getClassroomList(courseId, page);
        model.addAttribute("classrooms", classroomData.get("classrooms"));
        model.addAttribute("total", classroomData.get("total"));
        model.addAttribute("currentPage", classroomData.get("currentPage"));
        model.addAttribute("totalPages", classroomData.get("totalPages"));
        model.addAttribute("courseId", courseId);
        return "flone/class-room-list";
    }

    /**
     * 강의룸 등록 폼을 반환하는 메서드입니다.
     *
     * @param courseId 강의의 고유 ID
     * @param model 뷰로 데이터를 전달하기 위한 모델 객체
     * @return 강의룸 등록 폼 페이지 경로
     */
    @GetMapping("classroom-register-form.hm")
    public String getRegisterForm(@RequestParam(value = "courseId") int courseId, Model model) {
        model.addAttribute("courseId", courseId);
        return "flone/classroom-register-form";
    }

    /**
     * 강의룸을 등록하고, 강의룸 목록 페이지로 리다이렉트하는 메서드입니다.
     *
     * @param classroomName 강의룸 이름
     * @param description 강의룸 설명
     * @param courseId 강의의 고유 ID
     * @param imageFile 강의룸 이미지 파일
     * @return 강의룸 목록 페이지로 리다이렉트
     */
    @PostMapping("classroom-register.hm")
    public String registerClassroom(
            @RequestParam("classroomName") String classroomName,
            @RequestParam("description") String description,
            @RequestParam("courseId") int courseId,
            @RequestParam("imageFile") MultipartFile imageFile) {

        ClassroomRegisterRequest request = new ClassroomRegisterRequest();
        request.setClassroomName(classroomName);
        request.setDescription(description);
        request.setCourseId(courseId);
        request.setImageFile(imageFile);

        classroomService.registerClassroom(request);
        return "redirect:classroom-list.hm?courseId=" + courseId;
    }

    /**
     * 강의룸을 삭제하고, 강의룸 목록 페이지로 리다이렉트하는 메서드입니다.
     *
     * @param classroomId 삭제할 강의룸의 고유 ID
     * @param courseId 강의의 고유 ID
     * @return 강의룸 목록 페이지로 리다이렉트
     */
    @PostMapping("classroom-delete.hm")
    public String deleteClassroom(
            @RequestParam("classroomId") int classroomId,
            @RequestParam("courseId") int courseId) {

        classroomService.deleteClassroom(classroomId);
        return "redirect:classroom-list.hm?courseId=" + courseId;
    }
}
