package hayan.board.dto;

import hayan.board.entity.Board;
import lombok.Getter;

@Getter
public class ResponseDto {

    private String userName;
    private String title;
    private String content;

    public ResponseDto(Board entity) {
        this.userName = entity.getUserName();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
