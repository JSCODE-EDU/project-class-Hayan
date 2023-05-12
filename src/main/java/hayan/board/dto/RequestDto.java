package hayan.board.dto;

import hayan.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class RequestDto {

    @Getter
    @NoArgsConstructor
    public static class Post {
        private String userName;
        private String title;
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
                    .createdAt(LocalDateTime.now())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Update {

        private String title;
        private String content;

        public Update(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }
}
