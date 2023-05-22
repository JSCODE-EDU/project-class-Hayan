package hayan.board.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorInformation {

    // 400
    REQUEST_VALIDATION_FAIL(HttpStatus.BAD_REQUEST, "유효한 요청 값이 아닙니다."),

    // 404
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."),

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다. 관리자에게 문의 주세요.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorInformation(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
