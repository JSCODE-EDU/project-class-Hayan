package hayan.board.service;

import hayan.board.dto.RequestDto;
import hayan.board.entity.Board;
import hayan.board.repository.BoardRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Transactional
@Rollback
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;

    private Board board1;
    private Board board2;
    @BeforeEach
    public void setup() {
        String userName1 = "testUser1";
        String title1 = "testTitle1";
        String content1 = "testContent1";
        RequestDto.Post requestDto1 = new RequestDto.Post(userName1, title1, content1);

        String userName2 = "testUser2";
        String title2 = "testTitle2";
        String content2 = "testContent2";
        RequestDto.Post requestDto2 = new RequestDto.Post(userName2, title2, content2);

        board1 = boardService.post(requestDto1);
        board2 = boardService.post(requestDto2);
    }

    @DisplayName("게시글 저장")
    @Test
    void post() {

        String userName = "testUser";
        String title = "testTitle";
        String content = "testContent";
        RequestDto.Post requestDto = new RequestDto.Post(userName, title, content);

        Board findboard = boardService.post(requestDto);

        assertThat(findboard.getUserName()).isEqualTo(userName);
        assertThat(findboard.getTitle()).isEqualTo(title);
        assertThat(findboard.getContent()).isEqualTo(content);
    }

    @DisplayName("전체 게시글 조회")
    @Test
    void findAll() {
        List<Board> posts = boardService.findAll();

        assertThat(posts.size()).isEqualTo(2);
    }

    @DisplayName("특정 게시글 조회")
    @Test
    public void findbyId() {
        Board board = boardService.findOne(board1.getId()).orElse(null);

        assertThat(board.getUserName()).isEqualTo("testUser1");
        assertThat(board.getTitle()).isEqualTo("testTitle1");
        assertThat(board.getContent()).isEqualTo("testContent1");
    }

    @DisplayName("특정 게시글 수정")
    @Test
    public void updateBoard() {
        RequestDto.Update updateRequestDto = new RequestDto.Update("updateTitle", "updateContent");
        Board board = boardService.updateBoard(board1.getId(), updateRequestDto);

        assertThat(board.getTitle()).isEqualTo("updateTitle");
        assertThat(board.getContent()).isEqualTo("updateContent");
    }

    @DisplayName("게시글 삭제")
    @Test
    public void deleteById() {
        boardService.deleteById(board1.getId());

        assertThat(boardRepository.findAll().size()).isEqualTo(1);
    }
}