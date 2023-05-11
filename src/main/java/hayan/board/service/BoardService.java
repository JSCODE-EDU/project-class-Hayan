package hayan.board.service;

import hayan.board.entity.Board;
import hayan.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long post(String user_name, String title, String content) {
        Board board = Board.createBoard(user_name, title, content);
        boardRepository.save(board);

        return board.getId();
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Optional<Board> findOne(Long post_id) {
        return boardRepository.findById(post_id);
    }

    @Transactional
    public void updateBoard(Long post_id, String title, String content) {
        Board board = boardRepository.findById(post_id).orElseThrow();
        board.update(title, content);
    }

    @Transactional
    public void deleteById(Long post_id) {
        boardRepository.deleteById(post_id);
    }
}
