package hayan.board.service;

import hayan.board.dto.RequestDto;
import hayan.board.domain.Board;
import hayan.board.dto.ResponseDto;
import hayan.board.repository.BoardRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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

    @Test
    void 게시글_저장() {

        String userName = "testUser";
        String title = "testTitle";
        String content = "testContent";
        RequestDto.Post requestDto = new RequestDto.Post(userName, title, content);

        Board findboard = boardService.post(requestDto);

        assertThat(findboard.getUserName()).isEqualTo(userName);
        assertThat(findboard.getTitle()).isEqualTo(title);
        assertThat(findboard.getContent()).isEqualTo(content);
    }

    @Test
    void 전체_게시글_조회() {
        List<ResponseDto> posts = boardService.findAll();

        assertThat(posts.size()).isEqualTo(5);
    }

    @Test
    void boardId로_게시글_조회() {
        Board board = boardService.findOne(board1.getId());

        assertThat(board.getUserName()).isEqualTo("testUser1");
        assertThat(board.getTitle()).isEqualTo("testTitle1");
        assertThat(board.getContent()).isEqualTo("testContent1");
    }

    @Test
    void 특정_게시글_수정() {
        RequestDto.Update updateRequestDto = new RequestDto.Update("updateTitle", "updateContent");
        Board board = boardService.updateBoard(board1.getId(), updateRequestDto);

        assertThat(board.getTitle()).isEqualTo("updateTitle");
        assertThat(board.getContent()).isEqualTo("updateContent");
    }

    @Test
    void boardId로_게시글_삭제() {
        boardService.deleteById(board1.getId());

        assertThat(boardRepository.findAll().size()).isEqualTo(4);
    }
}