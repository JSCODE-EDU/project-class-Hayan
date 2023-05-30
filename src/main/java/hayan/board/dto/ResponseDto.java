package hayan.board.dto;

import hayan.board.domain.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "응답 DTO")
@Getter
public class ResponseDto {

    @Schema(description = "게시글 번호")
    private Long boardId;
    @Schema(description = "작성자")
    private String userName;
    @Schema(description = "게시글 제목")
    private String title;
    @Schema(description = "내용")
    private String content;
    @Schema(description = "작성일자")
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
