package org.hermez.classroom.board.controller;

import org.hermez.classroom.board.dto.BoardEditRequest;
import org.hermez.classroom.board.dto.BoardRegisterRequest;
import org.hermez.classroom.board.model.Board;
import org.hermez.classroom.board.service.BoardService;
import org.hermez.classroom.reply.model.Reply;
import org.hermez.classroom.reply.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 게시판 관련 요청을 처리하는 컨트롤러 클래스입니다.
 *
 * @author 김혁진
 */
@Controller
@RequestMapping("flone/board")
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;

    /**
     * BoardController 생성자입니다.
     *
     * @param boardService 게시판 서비스
     * @param replyService 댓글 서비스
     */
    public BoardController(BoardService boardService, ReplyService replyService) {
        this.boardService = boardService;
        this.replyService = replyService;
    }

    /**
     * 특정 강의실의 게시판 목록을 조회합니다.
     *
     * @param classroomId 강의실 ID
     * @param model       모델 객체
     * @return 게시판 목록 페이지의 뷰 이름
     */
    @GetMapping("board-list.hm")
    public String getClassroomBoardList(@RequestParam("classroomId") int classroomId, Model model) {
        List<Board> boards = boardService.getBoardsByClassroomId(classroomId);
        model.addAttribute("boards", boards);
        model.addAttribute("classroomId", classroomId);
        return "flone/board-list";
    }

    /**
     * 게시판의 상세 정보를 조회합니다.
     *
     * @param classroomId 강의실 ID
     * @param boardId     게시판 ID
     * @param model       모델 객체
     * @return 게시판 상세 페이지의 뷰 이름
     */
    @GetMapping("board-detail.hm")
    public String getBoardDetail(@RequestParam("classroomId") int classroomId, @RequestParam("boardId") int boardId, Model model) {
        Board board = boardService.getBoardById(boardId);
        List<Reply> replies = replyService.getRepliesByBoardId(boardId);
        model.addAttribute("board", board);
        model.addAttribute("replies", replies);
        return "flone/board-detail";
    }

    /**
     * 게시판 등록 폼을 반환합니다.
     *
     * @param classroomId 강의실 ID
     * @param model       모델 객체
     * @return 게시판 등록 폼 페이지의 뷰 이름
     */
    @GetMapping("board-register-form.hm")
    public String getBoardRegisterForm(@RequestParam("classroomId") int classroomId, Model model) {
        model.addAttribute("classroomId", classroomId);
        model.addAttribute("boardRegisterRequest", new BoardRegisterRequest());
        return "flone/board-register-form";
    }

    /**
     * 게시판을 등록합니다.
     *
     * @param request 게시판 등록 요청 데이터
     * @return 게시판 목록 페이지로 리다이렉트
     */
    @PostMapping("board-register.hm")
    public String postBoardRegister(BoardRegisterRequest request) {
        boardService.registerBoard(request);
        return "redirect:board-list.hm?classroomId=" + request.getClassroomId();
    }

    /**
     * 게시판 수정 폼을 반환합니다.
     *
     * @param boardId 게시판 ID
     * @param model   모델 객체
     * @return 게시판 수정 폼 페이지의 뷰 이름
     */
    @GetMapping("board-edit-form.hm")
    public String getBoardEditForm(@RequestParam("boardId") int boardId, Model model) {
        Board board = boardService.getBoardById(boardId);
        model.addAttribute("board", board);
        return "flone/board-edit-form";
    }

    /**
     * 게시판을 수정합니다.
     *
     * @param request 게시판 수정 요청 데이터
     * @return 게시판 상세 페이지로 리다이렉트
     */
    @PostMapping("board-edit.hm")
    public String postBoardEdit(BoardEditRequest request) {
        boardService.updateBoard(request);
        return "redirect:board-detail.hm?classroomId=" + request.getClassroomId()
                + "&boardId=" + request.getBoardId();
    }

    /**
     * 게시판을 삭제합니다.
     *
     * @param boardId     게시판 ID
     * @param classroomId 강의실 ID
     * @return 게시판 목록 페이지로 리다이렉트
     */
    @PostMapping("board-delete.hm")
    public String postBoardDelete(@RequestParam("boardId") int boardId, @RequestParam("classroomId") int classroomId) {
        boardService.deleteBoard(boardId);
        return "redirect:board-list.hm?classroomId=" + classroomId;
    }
}
