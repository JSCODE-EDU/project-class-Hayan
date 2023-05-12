package hayan.board.controller;

import hayan.board.dto.RequestDto;
import hayan.board.dto.ResponseDto;
import hayan.board.entity.Board;
import hayan.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseDto post(@RequestBody RequestDto.Post postRequestDto) {
        Board board = boardService.post(postRequestDto);

        return ResponseDto.of(board);
    }

    @PatchMapping("/{boardId}")
    public ResponseDto update(@PathVariable Long boardId, @RequestBody RequestDto.Update updateRequestDto) {

        Board board = boardService.updateBoard(boardId, updateRequestDto);

        return ResponseDto.of(board);
    }
}
