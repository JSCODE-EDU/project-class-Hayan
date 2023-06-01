package hayan.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestDto {

    private String email;
    private String password;

    @Builder
    public RequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
