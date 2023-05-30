package hayan.board.controller;

import hayan.board.dto.RequestDto;
import hayan.board.dto.ResponseDto;
import hayan.board.domain.Board;
import hayan.board.exception.ErrorInformation;
import hayan.board.exception.ErrorResponse;
import hayan.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
@Validated
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "게시글 작성", description = "새 게시글을 작성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "유효한 요청 값이 아닙니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다. 관리자에게 문의 주세요.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseDto post(@Valid @RequestBody RequestDto.Post postRequestDto) {

        Board board = boardService.post(postRequestDto);

        return ResponseDto.of(board);
    }

    @PatchMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "게시글 수정", description = "특정 게시글을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "유효한 요청 값이 아닙니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다. 관리자에게 문의 주세요.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseDto update(@PathVariable Long boardId, @Valid @RequestBody RequestDto.Update updateRequestDto) {

        Board board = boardService.updateBoard(boardId, updateRequestDto);

        return ResponseDto.of(board);
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "id로 게시글 조회", description = "boardId로 게시글을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다. 관리자에게 문의 주세요.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseDto searchById(@PathVariable Long boardId) {

        Board board = boardService.findOne(boardId);

        return ResponseDto.of(board);
    }

    @GetMapping("/keyword")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "keyword로 게시글 조회", description = "제목에 keyword를 포함하는 게시글을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "유효한 요청 값이 아닙니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다. 관리자에게 문의 주세요.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<ResponseDto> searchAllByKeyword(@Valid @RequestParam RequestDto.Keyword keyword) {

        List<ResponseDto> boards = boardService.findAllByKeyword(keyword.getKeyword());

        return boards;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "전체 게시글 조회", description = "전체 게시글을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다. 관리자에게 문의 주세요.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<ResponseDto> searchAll() {

        List<ResponseDto> boards = boardService.findAll();

        return boards;
    }

    @DeleteMapping("/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "게시글 삭제", description = "특정 게시글을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다. 관리자에게 문의 주세요.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public void delete(@PathVariable Long boardId) {

        boardService.deleteById(boardId);
    }
}
