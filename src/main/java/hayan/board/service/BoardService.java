package hayan.board.service;

import hayan.board.dto.RequestDto;
import hayan.board.domain.Board;
import hayan.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board post(RequestDto.Post postRequestDto) {
        Board board = postRequestDto.toEntity();

        return boardRepository.save(board);
    }

    public List<Board> findAll() {
        Pageable pageable = PageRequest.of(0, 100);

        return boardRepository.findTop100ByOrderByCreatedAtDesc(pageable);
//        return boardRepository.findAll();
    }

    public Board findOne(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        return board;
    }

    public List<Board> findAllByTitle(String keyword) {
        Pageable pageable = PageRequest.of(0, 100);

        return boardRepository.findTop100ByTitleContainingOrderByCreatedAtDesc(pageable, keyword);
    }

    @Transactional
    public Board updateBoard(Long boardId, RequestDto.Update updateRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        board.update(updateRequestDto.getTitle(), updateRequestDto.getContent());

        return board;
    }

    @Transactional
    public void deleteById(Long boardId) {
        boardRepository.deleteById(boardId);
    }



}
