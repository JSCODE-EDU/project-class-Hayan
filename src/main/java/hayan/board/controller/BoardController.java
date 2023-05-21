package hayan.board.controller;

import hayan.board.dto.RequestDto;
import hayan.board.dto.ResponseDto;
import hayan.board.domain.Board;
import hayan.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto post(@Valid @RequestBody RequestDto.Post postRequestDto) {

        Board board = boardService.post(postRequestDto);

        return ResponseDto.of(board);
    }

    @PatchMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto update(@PathVariable Long boardId, @Valid @RequestBody RequestDto.Update updateRequestDto) {

        Board board = boardService.updateBoard(boardId, updateRequestDto);

        return ResponseDto.of(board);
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto searchById(@PathVariable Long boardId) {

        Board board = boardService.findOne(boardId);

        return ResponseDto.of(board);
    }

    @GetMapping("/keyword")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseDto> searchAllByKeyword(@Valid @RequestParam RequestDto.Keyword keyword) {

        List<ResponseDto> boards = boardService.findAllByKeyword(keyword.getKeyword());

        return boards;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseDto> searchAll() {

        List<ResponseDto> boards = boardService.findAll();

        return boards;
    }

    @DeleteMapping("/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long boardId) {

        boardService.deleteById(boardId);
    }
}
