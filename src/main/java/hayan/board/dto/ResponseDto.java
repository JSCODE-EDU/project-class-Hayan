package hayan.board.dto;

import hayan.board.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseDto {

    private Long boardId;
    private String userName;
    private String title;
    private String content;
    private LocalDateTime created;

    public ResponseDto(Board entity) {
        this.boardId = entity.getId();
        this.userName = entity.getUserName();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.created = entity.getCreatedAt();
    }

    public static ResponseDto of(Board data) {
        return new ResponseDto(data);
    }
}
