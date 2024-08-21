package org.hermez.classroom.board.dto;

import lombok.Getter;
import lombok.Setter;
import org.hermez.classroom.board.model.Board;

import java.util.List;

@Getter
@Setter
public class BoardListResponse {

    private int classroomId;
    private String videoLink;
    private List<Board> boards;

    public BoardListResponse() {
    }

    public BoardListResponse(int classroomId, String videoLink, List<Board> boards) {
        this.classroomId = classroomId;
        this.videoLink = videoLink;
        this.boards = boards;
    }
}
