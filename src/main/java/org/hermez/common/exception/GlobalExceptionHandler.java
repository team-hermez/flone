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

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException ex, Model model) {
        model.addAttribute("errorMessage", "데이터가 존재하지 않습니다.");
        return "error";
    }

    @ExceptionHandler(ImageProcessingException.class)
    public String handleImageProcessingException(ImageProcessingException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(AdminFilterException.class)
    public String handleAdminFilterException(AdminFilterException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(ClassroomNotFoundException.class)
    public String handleClassroomServiceException(ClassroomNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(BoardServiceException.class)
    public String handleBoardServiceException(BoardServiceException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(ReplyNotFoundException.class)
    public String handleReplyServiceException(ReplyNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String handleNoHandlerFoundException() {
        return "/flone/error/404";
    }

    @ExceptionHandler(NotActiveClassException.class)
    public String handleNotActiveClassException(NotActiveClassException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
    @ExceptionHandler(CourseRegisterTimeException.class)
    public String handleCourseServiceException(CourseRegisterTimeException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}
