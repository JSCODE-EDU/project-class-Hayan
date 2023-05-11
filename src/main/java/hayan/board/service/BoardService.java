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
    public Long post(String userName, String title, String content) {
        Board board = Board.createBoard(userName, title, content);
        boardRepository.save(board);

        return board.getId();
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Optional<Board> findOne(Long postId) {
        return boardRepository.findById(postId);
    }

    @Transactional
    public void updateBoard(Long postId, String title, String content) {
        Board board = boardRepository.findById(postId).orElseThrow();
        board.update(title, content);
    }

    @Transactional
    public void deleteById(Long postId) {
        boardRepository.deleteById(postId);
    }

}
