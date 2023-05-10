package hayan.board.domain;

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
    @Column(name = "board_id")
    private Long id;

    private String name;
    private String password;
    private String title;
    private String content;
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Board(String name, String password, String title, String content) {
        this.name = name;
        this.password = password;
        this.title = title;
        this.content = content;
        createdAt = LocalDateTime.now();
    }
}
