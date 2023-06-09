package hayan.board.service;

import hayan.board.dto.RequestDto;
import hayan.board.domain.Board;
import hayan.board.dto.ResponseDto;
import hayan.global.exception.CustomException;
import hayan.global.exception.ErrorInformation;
import hayan.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ResponseDto> findAll() {
        List<Board> boards = boardRepository.findTop100ByOrderByCreatedAtDesc();

        return toResponses(boards);
    }

    public Board findOne(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(ErrorInformation.POST_NOT_FOUND));

        return board;
    }

    public List<ResponseDto> findAllByKeyword(String keyword) {
        List<Board> boards = boardRepository.findTop100ByTitleContainingOrderByCreatedAtDesc(keyword);

        return toResponses(boards);
    }

    @Transactional
    public Board updateBoard(Long boardId, RequestDto.Update updateRequestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(ErrorInformation.POST_NOT_FOUND));

        board.update(updateRequestDto.getTitle(), updateRequestDto.getContent());

        return board;
    }

    @Transactional
    public void deleteById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(ErrorInformation.POST_NOT_FOUND));

        boardRepository.deleteById(boardId);
    }

    public List<ResponseDto> toResponses(List<Board> boards) {

        return boards.stream()
                .map(board -> ResponseDto.of(board))
                .collect(Collectors.toList());
    }
}
