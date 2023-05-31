package hayan.global.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonPropertyOrder({"errorCode", "message"})
public class ErrorResponse {
    private String errorCode;
    private String message;

    public ErrorResponse(CustomException exception) {
        this.errorCode = exception.getErrorCode();
        this.message = exception.getMessage();
    }

    public ErrorResponse(ErrorInformation information) {
        this.errorCode = information.toString();
        this.message = information.getMessage();
    }

    public ErrorResponse(ErrorInformation information, String message) {
        this.errorCode = information.toString();
        this.message = message;
    }
}
