package hayan.board.controller;

import hayan.board.dto.PostRequestDto;
import hayan.board.dto.ResponseDto;
import hayan.board.dto.UpdateRequestDto;
import hayan.board.entity.Board;
import hayan.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/post")
    public ResponseDto post(@RequestBody PostRequestDto postRequestDto) {
        Long postId = boardService.post(postRequestDto);

        return new ResponseDto(boardService.findOne(postId).orElse(null));
    }

    @PatchMapping("/{postId}")
    public ResponseDto update(@PathVariable Long postId, @RequestBody UpdateRequestDto updateRequestDto) {
        Long id = boardService.updateBoard(postId, updateRequestDto);

        return new ResponseDto(boardService.findOne(id).orElse(null));
    }
}
