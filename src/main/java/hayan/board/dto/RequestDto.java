package hayan.board.dto;

import hayan.board.domain.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestDto {

    @Schema(description = "게시글 작성 DTO")
    @Getter
    @NoArgsConstructor
    public static class Post {
        @Schema(description = "작성자")
        @NotNull(message = "닉네임을 입력해주세요.")
        private String userName;

        @Schema(description = "게시글 제목")
        @NotBlank(message = "제목은 공백으로만 이루어질 수 없습니다.")
        @Size(min = 1, max = 15, message = "제목은 1글자 이상 15글자 이하로 입력되어야 합니다.")
        private String title;

        @Schema(description = "내용")
        @NotNull(message = "내용을 입력해주세요.")
        @Size(min = 1, max = 1000, message = "내용은 1글자 이상 1000글자 이하로 입력되어야 합니다.")
        private String content;

        @Builder
        public Post(String userName, String title, String content) {
            this.userName = userName;
            this.title = title;
            this.content = content;
        }

        public Board toEntity() {
            return Board.builder()
                    .userName(userName)
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    @Schema(description = "게시글 수정 DTO")
    @Getter
    @NoArgsConstructor
    public static class Update {

        @Schema(description = "변결할 제목")
        @NotBlank(message = "제목은 공백으로만 이루어질 수 없습니다.")
        @Size(min = 1, max = 15, message = "제목은 1글자 이상 15글자 이하로 입력되어야 합니다.")
        private String title;

        @Schema(description = "변경할 내용")
        @NotNull(message = "내용을 입력해주세요.")
        @Size(min = 1, max = 1000, message = "내용은 1글자 이상 1000글자 이하로 입력되어야 합니다.")
        private String content;

        public Update(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    @Schema(description = "키워드")
    @Getter
    public static class Keyword {

        @Schema(description = "검색 키워드")
        @NotBlank(message = "검색 키워드는 공백을 제외한 1글자 이상이어야 합니다.")
        private String keyword;

        public Keyword(String keyword) {
            this.keyword = keyword;
        }
    }
}
