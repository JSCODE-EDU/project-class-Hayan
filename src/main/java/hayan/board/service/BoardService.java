package hayan.board.service;

import hayan.board.dto.PostRequestDto;
import hayan.board.dto.UpdateRequestDto;
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
    public Long post(PostRequestDto postRequestDto) {
        Board board = postRequestDto.toEntity();
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
    public Long updateBoard(Long postId, UpdateRequestDto updateRequestDto) {
        Board board = boardRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        board.update(updateRequestDto.getTitle(), updateRequestDto.getContent());

        return postId;
    }

    @Transactional
    public void deleteById(Long postId) {
        boardRepository.deleteById(postId);
    }

}
