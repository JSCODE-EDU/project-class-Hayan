package hayan.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String user_name;
    private String title;
    @Column(columnDefinition = "text")
    private String content;
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Board(String user_name, String title, String content, LocalDateTime createdAt) {
        this.user_name = user_name;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Board createBoard(String user_name, String title, String content) {
        return Board.builder()
                .user_name(user_name)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
