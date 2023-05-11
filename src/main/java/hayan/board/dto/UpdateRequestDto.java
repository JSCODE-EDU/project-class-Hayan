package hayan.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public UpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
