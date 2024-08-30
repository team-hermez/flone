package org.hermez.common.exception;

import static org.springframework.http.HttpStatus.*;

import org.hermez.classroom.board.exception.BoardServiceException;
import org.hermez.classroom.classroom.exception.ClassroomNotFoundException;
import org.hermez.classroom.reply.exception.ReplyNotFoundException;
import org.hermez.course.exception.CourseRegisterTimeException;
import org.hermez.filter.exception.AdminFilterException;
import org.hermez.image.exception.ImageProcessingException;
import org.hermez.reservation.exception.NotActiveClassException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 전역 예외 처리 핸들러입니다. 이 클래스는 애플리케이션 전체에서 발생할 수 있는 예외를 처리하여 적절한 에러 페이지를 반환합니다.
 *
 * @author 김혁진
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 모든 예외를 처리하는 메서드입니다.
     *
     * @param ex 발생한 예외 객체
     * @param model 에러 메시지를 전달하기 위한 모델 객체
     * @return 에러 페이지 뷰 이름
     */
    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /**
     * NullPointerException을 처리하는 메서드입니다.
     *
     * @param ex 발생한 NullPointerException 객체
     * @param model 에러 메시지를 전달하기 위한 모델 객체
     * @return 에러 페이지 뷰 이름
     */
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException ex, Model model) {
        model.addAttribute("errorMessage", "데이터가 존재하지 않습니다.");
        return "error";
    }

    /**
     * ImageProcessingException을 처리하는 메서드입니다.
     *
     * @param ex 발생한 ImageProcessingException 객체
     * @param model 에러 메시지를 전달하기 위한 모델 객체
     * @return 에러 페이지 뷰 이름
     */
    @ExceptionHandler(ImageProcessingException.class)
    public String handleImageProcessingException(ImageProcessingException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /**
     * AdminFilterException을 처리하는 메서드입니다.
     *
     * @param ex 발생한 AdminFilterException 객체
     * @param model 에러 메시지를 전달하기 위한 모델 객체
     * @return 에러 페이지 뷰 이름
     */
    @ExceptionHandler(AdminFilterException.class)
    public String handleAdminFilterException(AdminFilterException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /**
     * ClassroomNotFoundException을 처리하는 메서드입니다.
     *
     * @param ex 발생한 ClassroomNotFoundException 객체
     * @param model 에러 메시지를 전달하기 위한 모델 객체
     * @return 에러 페이지 뷰 이름
     */
    @ExceptionHandler(ClassroomNotFoundException.class)
    public String handleClassroomServiceException(ClassroomNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /**
     * BoardServiceException을 처리하는 메서드입니다.
     *
     * @param ex 발생한 BoardServiceException 객체
     * @param model 에러 메시지를 전달하기 위한 모델 객체
     * @return 에러 페이지 뷰 이름
     */
    @ExceptionHandler(BoardServiceException.class)
    public String handleBoardServiceException(BoardServiceException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /**
     * ReplyNotFoundException을 처리하는 메서드입니다.
     *
     * @param ex 발생한 ReplyNotFoundException 객체
     * @param model 에러 메시지를 전달하기 위한 모델 객체
     * @return 에러 페이지 뷰 이름
     */
    @ExceptionHandler(ReplyNotFoundException.class)
    public String handleReplyServiceException(ReplyNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /**
     * NoHandlerFoundException을 처리하여 404 에러 페이지를 반환하는 메서드입니다.
     *
     * @return 404 에러 페이지 뷰 이름
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String handleNoHandlerFoundException() {
        return "/flone/error/404";
    }

    /**
     * NotActiveClassException을 처리하는 메서드입니다.
     *
     * @param ex 발생한 NotActiveClassException 객체
     * @param model 에러 메시지를 전달하기 위한 모델 객체
     * @return 에러 페이지 뷰 이름
     */
    @ExceptionHandler(NotActiveClassException.class)
    public String handleNotActiveClassException(NotActiveClassException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /**
     * CourseRegisterTimeException을 처리하는 메서드입니다.
     *
     * @param ex 발생한 CourseRegisterTimeException 객체
     * @param model 에러 메시지를 전달하기 위한 모델 객체
     * @return 에러 페이지 뷰 이름
     */
    @ExceptionHandler(CourseRegisterTimeException.class)
    public String handleCourseServiceException(CourseRegisterTimeException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}
