package hayan.member.dto;

import hayan.member.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseDto {

    private Long memberId;
    private String email;
    private LocalDateTime createdAt;

    public ResponseDto(Member entity) {
        this.memberId = entity.getId();
        this.email = entity.getEmail();
        this.createdAt = entity.getCreatedAt();
    }

    public static ResponseDto of(Member data) {
        return new ResponseDto(data);
    }
}
