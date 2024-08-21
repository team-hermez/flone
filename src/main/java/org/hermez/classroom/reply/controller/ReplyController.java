package org.hermez.classroom.reply.controller;

import org.hermez.classroom.reply.dto.ReplyRegisterRequest;
import org.hermez.classroom.reply.model.Reply;
import org.hermez.classroom.reply.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 댓글 기능을 처리하는 컨트롤러입니다.

 * @author 김혁진
 */
@Controller
public class ReplyController {

    private final ReplyService replyService;

    /**
     * ReplyController의 생성자입니다.
     *
     * @param replyService 댓글 관련 비즈니스 로직을 처리하는 서비스
     */
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    /**
     * 특정 게시판에 대한 댓글 목록을 조회합니다.
     *
     * @param boardId 댓글을 조회할 게시판의 ID
     * @param model 댓글 목록을 저장할 모델 객체
     * @return 댓글 목록 페이지의 뷰 이름
     */
    @GetMapping("/reply-list.hm")
    public String getReplyList(@RequestParam("boardId") int boardId, Model model) {
        List<Reply> replies = replyService.getRepliesByBoardId(boardId);
        model.addAttribute("replies", replies);
        return "flone/reply-list";
    }

    /**
     * 새로운 댓글을 등록합니다.
     *
     * @param replyRequest 댓글 등록 요청 데이터를 담고 있는 DTO
     * @return 등록 후 해당 게시판 상세 페이지로 리다이렉트
     */
    @PostMapping("/reply-register.hm")
    public String postRegisterReply(@ModelAttribute ReplyRegisterRequest replyRequest) {
        replyService.registerReply(replyRequest);
        return "redirect:/board-detail.hm?boardId=" + replyRequest.getBoardId();
    }

    /**
     * 특정 댓글을 삭제합니다.
     *
     * @param replyId 삭제할 댓글의 ID
     * @param boardId 댓글이 속한 게시판의 ID
     * @return 삭제 후 해당 게시판 상세 페이지로 리다이렉트
     */
    @PostMapping("/reply-delete.hm")
    public String deleteReply(@RequestParam("replyId") int replyId, @RequestParam("boardId") int boardId) {
        replyService.deleteReply(replyId);
        return "redirect:/board-detail.hm?boardId=" + boardId;
    }
}
