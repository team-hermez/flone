package org.hermez.common.exception;

import org.hermez.classroom.board.exception.BoardServiceException;
import org.hermez.classroom.classroom.exception.ClassroomNotFoundException;
import org.hermez.classroom.reply.exception.ReplyNotFoundException;
import org.hermez.image.exception.ImageProcessingException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(ClassroomNotFoundException.class)
    public String handleClassroomServiceException(BoardServiceException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(BoardServiceException.class)
    public String handleBoardServiceException(BoardServiceException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(ReplyNotFoundException.class)
    public String handleReplyServiceException(BoardServiceException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}
