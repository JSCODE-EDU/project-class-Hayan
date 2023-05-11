package hayan.board.service;

import hayan.board.entity.Board;
import hayan.board.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;

    private static Long postId1;
    private static Long postId2;

    @BeforeEach
    public void setup() {
        String userName1 = "testUser1";
        String title1 = "testTitle1";
        String content1 = "testContent1";

        String userName2 = "testUser2";
        String title2 = "testTitle2";
        String content2 = "testContent2";

        postId1 = boardService.post(userName1, title1, content1);
        postId2 = boardService.post(userName2, title2, content2);
    }

    @DisplayName("게시글 저장")
    @Test
    public void post() {

        String userName = "testUser";
        String title = "testTitle";
        String content = "testContent";

        Long postId = boardService.post(userName, title, content);
        Board findboard = boardRepository.findById(postId).orElse(null);

        assertThat(findboard.getUserName()).isEqualTo(userName);
        assertThat(findboard.getTitle()).isEqualTo(title);
        assertThat(findboard.getContent()).isEqualTo(content);
    }

    @DisplayName("전체 게시글 조회")
    @Test
    public void findAll() {
        List<Board> posts = boardService.findAll();

        assertThat(posts.size()).isEqualTo(2);
    }

    @DisplayName("특정 게시글 조회")
    @Test
    public void findbyId() {
        Board board = boardService.findOne(postId1).orElse(null);

        assertThat(board.getUserName()).isEqualTo("testUser1");
        assertThat(board.getTitle()).isEqualTo("testTitle1");
        assertThat(board.getContent()).isEqualTo("testContent1");
    }

    @DisplayName("특정 게시글 수정")
    @Test
    public void updateBoard() {
        boardService.updateBoard(postId1, "updateTitle", "updateContent");

        Board board = boardRepository.findById(postId1).orElse(null);

        assertThat(board.getTitle()).isEqualTo("updateTitle");
        assertThat(board.getContent()).isEqualTo("updateContent");
    }

    @DisplayName("게시글 삭제")
    @Test
    public void deleteById() {
        boardService.deleteById(postId1);

        assertThat(boardRepository.findAll().size()).isEqualTo(1);
    }
}