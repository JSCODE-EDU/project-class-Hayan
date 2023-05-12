package hayan.board.dto;

import hayan.board.entity.Board;
import lombok.Getter;

@Getter
public class ResponseDto {

    private Long boardId;
    private String userName;
    private String title;
    private String content;

    public ResponseDto(Board entity) {
        this.boardId = entity.getId();
        this.userName = entity.getUserName();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

    public static ResponseDto of(Board data) {
        return new ResponseDto(data);
    }
}
